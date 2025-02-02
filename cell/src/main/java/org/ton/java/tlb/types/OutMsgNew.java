package org.ton.java.tlb.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ton.java.cell.Cell;
import org.ton.java.cell.CellBuilder;

@Builder
@Getter
@Setter
@ToString
/**
 msg_export_new$001 out_msg:^MsgEnvelope
 transaction:^Transaction = OutMsg;
 */
public class OutMsgNew implements OutMsg {
    MsgEnvelope outMsg;
    Transaction transaction;

    @Override
    public Cell toCell() {
        return CellBuilder.beginCell()
                .storeUint(0b001, 3)
                .storeRef(outMsg.toCell())
                .storeRef(transaction.toCell())
                .endCell();
    }
}
