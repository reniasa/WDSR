package mypackage.transaction;

public class ResolvedTransactionReporter {

    private ResolvedTransactionReceiver receiver;

    public ResolvedTransactionReporter(ResolvedTransactionReceiver receiver) {
        this.receiver = receiver;
    }

    public void sendTransactionToCustomer(Transaction transaction) {
        receiver.receiveTransaction(transaction);
    }
}
