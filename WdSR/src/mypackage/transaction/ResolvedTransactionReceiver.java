package mypackage.transaction;

/**
 * Created by renuasa on 6/15/2016.
 */
public interface ResolvedTransactionReceiver {

    void receiveTransaction(Transaction transaction);
}
