package org.ton.java.smartcontract;

import com.iwebpp.crypto.TweetNaclFast;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ton.java.address.Address;
import org.ton.java.smartcontract.types.InitExternalMessage;
import org.ton.java.smartcontract.types.WalletVersion;
import org.ton.java.smartcontract.wallet.Options;
import org.ton.java.smartcontract.wallet.Wallet;
import org.ton.java.smartcontract.wallet.v3.WalletV3ContractR1;
import org.ton.java.tonlib.Tonlib;
import org.ton.java.utils.Utils;

import java.math.BigInteger;

@Slf4j

public class GenerateWallet {

    public static TestWallet random(Tonlib tonlib, long initialBalanceInToncoins) throws InterruptedException {
        TweetNaclFast.Signature.KeyPair keyPair;
        WalletV3ContractR1 adminWallet;
        String predefinedSecretKey = "";

        if (StringUtils.isEmpty(predefinedSecretKey)) {
            keyPair = Utils.generateSignatureKeyPair();
        } else {
            keyPair = Utils.generateSignatureKeyPairFromSeed(Utils.hexToSignedBytes(predefinedSecretKey));
        }

        log.info("pubKey {}, prvKey {}", Utils.bytesToHex(keyPair.getPublicKey()), Utils.bytesToHex(keyPair.getSecretKey()));

        Options options = Options.builder()
                .publicKey(keyPair.getPublicKey())
                .wc(0)
                .build();

        Wallet walletcontract = new Wallet(WalletVersion.V3R1, options);
        adminWallet = walletcontract.create();

        InitExternalMessage msg = adminWallet.createInitExternalMessage(keyPair.getSecretKey());
        Address address = msg.address;

        String nonBounceableAddress = address.toString(true, true, false, true);
        String bounceableAddress = address.toString(true, true, true, true);

        log.info("\nNon-bounceable address (for init): {}\nBounceable address (for later access): {}\nraw: {}\n", nonBounceableAddress, bounceableAddress, address.toString(false));

        if (StringUtils.isEmpty(predefinedSecretKey)) {
            BigInteger balance = TestFaucet.topUpContract(tonlib, Address.of(nonBounceableAddress), Utils.toNano(initialBalanceInToncoins));
            log.info("new wallet balance {}", Utils.formatNanoValue(balance));
            // deploy new wallet
            tonlib.sendRawMessage(msg.message.toBase64());
            Utils.sleep(20);
        }
        return TestWallet.builder()
                .keyPair(keyPair)
                .wallet(adminWallet)
                .build();
    }
}
