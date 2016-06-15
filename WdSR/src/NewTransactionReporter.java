
public class NewTransactionReporter {


    private TransactionBackend transactionBackend;

    public NewTransactionReporter(TransactionBackend transactionBackend) {
        this.transactionBackend = transactionBackend;
    }

    public NewTransactionResult reportNewTransaction(Transaction transaction) {
        NewTransactionResult newTransactionResult = new NewTransactionResult();
        newTransactionResult.id = transactionBackend.sendTransaction(transaction);
        if(newTransactionResult.id < -1){
            throw new InvalidTransactionIdException();
        }
        newTransactionResult.isSuccess = newTransactionResult.id != -1;
        return newTransactionResult;
    }
}
