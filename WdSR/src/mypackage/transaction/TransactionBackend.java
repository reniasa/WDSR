package mypackage.transaction;

/**
 * Created by renuasa on 6/1/2016.
 */
public interface TransactionBackend {
    int sendTransaction(Transaction transaction);
}
