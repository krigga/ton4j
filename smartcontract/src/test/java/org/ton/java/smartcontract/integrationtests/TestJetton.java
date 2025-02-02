package org.ton.java.smartcontract.integrationtests;

import com.iwebpp.crypto.TweetNaclFast;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.address.Address;
import org.ton.java.smartcontract.GenerateWallet;
import org.ton.java.smartcontract.TestWallet;
import org.ton.java.smartcontract.token.ft.JettonMinter;
import org.ton.java.smartcontract.token.ft.JettonWallet;
import org.ton.java.smartcontract.types.*;
import org.ton.java.smartcontract.wallet.Options;
import org.ton.java.smartcontract.wallet.Wallet;
import org.ton.java.smartcontract.wallet.WalletContract;
import org.ton.java.tonlib.Tonlib;
import org.ton.java.tonlib.types.FullAccountState;
import org.ton.java.utils.Utils;

import java.math.BigInteger;

@Slf4j
@RunWith(JUnit4.class)
public class TestJetton {
    public static final String NEW_ADMIN2 = "EQB6-6po0yspb68p7RRetC-hONAz-JwxG9514IEOKw_llXd5";

    static TestWallet adminWallet;
    static TestWallet wallet2;

    static Tonlib tonlib;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        tonlib = Tonlib.builder().testnet(true).build();
        adminWallet = GenerateWallet.random(tonlib, 7);
        wallet2 = GenerateWallet.random(tonlib, 1);

        long seqno = adminWallet.getWallet().getSeqno(tonlib);
        log.info("wallet seqno {}", seqno);
    }

    @Test
    public void testJetton() {

        log.info("admin wallet address {}", adminWallet.getWallet().getAddress().toString(true, true, true));
        log.info("second wallet address {}", wallet2.getWallet().getAddress().toString(true, true, true));

        JettonMinter minter = delployMinter();

        getMinterInfo(minter);

        // sequential calls to mint() sum up to totalSupply;
        minter.mint(tonlib, adminWallet.getWallet(), adminWallet.getWallet().getAddress(), Utils.toNano(0.05), Utils.toNano(0.04), Utils.toNano(100500), adminWallet.getKeyPair());
        Utils.sleep(20, "minting...");
        log.info("jetton total supply {}", minter.getTotalSupply(tonlib));

        //owner of adminWallet holds his jettons on jettonWallet
        Address adminJettonWalletAddress = minter.getJettonWalletAddress(tonlib, adminWallet.getWallet().getAddress());
        log.info("admin JettonWalletAddress {}", adminJettonWalletAddress.toString(true, true, true));

        JettonWallet adminJettonWallet = getJettonWalletInfo(adminJettonWalletAddress);

        editMinterContent(adminWallet.getWallet(), minter, "http://localhost/nft-marketplace/my_collection.1", adminWallet.getKeyPair());
        Utils.sleep(20);
        getMinterInfo(minter);

        log.info("newAdmin {}", Address.of(NEW_ADMIN2).toString(false));
        changeMinterAdmin(adminWallet.getWallet(), minter, Address.of(NEW_ADMIN2), adminWallet.getKeyPair());
        Utils.sleep(20);
        getMinterInfo(minter);

        Utils.sleep(15);
        FullAccountState wallet2State = tonlib.getAccountState(Address.of(wallet2.getWallet().getAddress()));

        log.info("wallet 2 balance " + wallet2State.getBalance());
        //transfer from admin to WALLET2_ADDRESS by sending transfer request to admin's jetton wallet
        transfer(adminWallet.getWallet(), adminJettonWallet.getAddress(), Address.of(wallet2.getWallet().getAddress()), Utils.toNano(555), adminWallet.getKeyPair());
        Utils.sleep(20);
        log.info("changed admin balance {}", Utils.formatNanoValue(adminJettonWallet.getBalance(tonlib)));

        //wallet 2 after received jettons, has JettonWallet assigned
        getJettonWalletInfo(minter.getJettonWalletAddress(tonlib, wallet2.getWallet().getAddress()));

        burn(adminWallet.getWallet(), adminJettonWallet.getAddress(), Utils.toNano(444), adminWallet.getWallet().getAddress(), adminWallet.getKeyPair());
        Utils.sleep(20);
        log.info("changed admin balance {}", Utils.formatNanoValue(adminJettonWallet.getBalance(tonlib)));
    }

    private JettonMinter delployMinter() {

        Options options = Options.builder()
                .adminAddress(adminWallet.getWallet().getAddress())
                .jettonContentUri("https://raw.githubusercontent.com/neodiX42/ton4j/dns-smc/1-media/neo-jetton.json")
                .jettonWalletCodeHex(WalletCodes.jettonWallet.getValue())
                .build();

        Wallet jettonMinter = new Wallet(WalletVersion.jettonMinter, options);
        JettonMinter minter = jettonMinter.create();
        log.info("jetton minter address {} {}", minter.getAddress().toString(true, true, true), minter.getAddress().toString(false));
        minter.deploy(tonlib, adminWallet.getWallet(), Utils.toNano(0.05), adminWallet.getKeyPair());
        Utils.sleep(25);
        return minter;
    }

    private JettonWallet getJettonWalletInfo(Address address) {
        Options optionsJettonWallet = Options.builder()
                .address(address)
                .build();

        Wallet wallet = new Wallet(WalletVersion.jettonWallet, optionsJettonWallet);
        JettonWallet jettonWallet = wallet.create();

        JettonWalletData data = jettonWallet.getData(tonlib);
        log.info("jettonWalletData {}", data);
        log.info("balance in jettons {}", Utils.formatNanoValue(data.getBalance()));
        return jettonWallet;
    }

    private void getMinterInfo(JettonMinter minter) {
        JettonMinterData data = minter.getJettonData(tonlib);
        log.info("JettonMinterData {}", data);
        log.info("minter adminAddress {} {}", data.getAdminAddress().toString(true, true, true), data.getAdminAddress().toString(false));
        log.info("minter totalSupply {}", Utils.formatNanoValue(data.getTotalSupply()));
    }


    private void editMinterContent(WalletContract adminWallet, JettonMinter minter, String newUriContent, TweetNaclFast.Signature.KeyPair keyPair) {

        long seqno = adminWallet.getSeqno(tonlib);

        ExternalMessage extMsg = adminWallet.createTransferMessage(
                keyPair.getSecretKey(),
                minter.getAddress(),
                Utils.toNano(0.05),
                seqno,
                minter.createEditContentBody(newUriContent, 0));

        tonlib.sendRawMessage(extMsg.message.toBase64());
    }

    private void changeMinterAdmin(WalletContract adminWallet, JettonMinter minter, Address newAdmin, TweetNaclFast.Signature.KeyPair keyPair) {

        long seqno = adminWallet.getSeqno(tonlib);

        ExternalMessage extMsg = adminWallet.createTransferMessage(
                keyPair.getSecretKey(),
                minter.getAddress(),
                Utils.toNano(0.05),
                seqno,
                minter.createChangeAdminBody(0, newAdmin));

        tonlib.sendRawMessage(extMsg.message.toBase64());
    }

    /**
     * @param jettonWalletAddress Address
     * @param toAddress           Address
     * @param jettonAmount        BigInteger
     * @param keyPair             KeyPair
     */
    private void transfer(WalletContract admin, Address jettonWalletAddress, Address toAddress, BigInteger jettonAmount, TweetNaclFast.Signature.KeyPair keyPair) {

        long seqno = admin.getSeqno(tonlib);

        ExternalMessage extMsg = admin.createTransferMessage(
                keyPair.getSecretKey(),
                Address.of(jettonWalletAddress),
                Utils.toNano(0.05),
                seqno,
                JettonWallet.createTransferBody(
                        0,
                        jettonAmount,
                        Address.of(toAddress), // destination
                        admin.getAddress(), // response address
                        Utils.toNano("0.01"), // forward amount
                        "gift".getBytes() // forward payload
                ));

        tonlib.sendRawMessage(extMsg.message.toBase64());
    }

    private void burn(WalletContract admin, Address jettonWalletAddress, BigInteger jettonAmount, Address responseAddress, TweetNaclFast.Signature.KeyPair keyPair) {

        long seqno = admin.getSeqno(tonlib);

        ExternalMessage extMsg = admin.createTransferMessage(
                keyPair.getSecretKey(),
                Address.of(jettonWalletAddress),
                Utils.toNano(0.05),
                seqno,
                JettonWallet.createBurnBody(
                        0,
                        jettonAmount,
                        responseAddress
                ));

        tonlib.sendRawMessage(extMsg.message.toBase64());
    }
}
