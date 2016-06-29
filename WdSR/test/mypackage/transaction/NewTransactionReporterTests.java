package mypackage.transaction;

import mypackage.exception.InvalidTransactionIdException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class NewTransactionReporterTests {

    private TransactionBackend transactionBackend;
    private Transaction transaction;
    private NewTransactionReporter reporter;

    @Before
    public void setUp(){
        transactionBackend = mock(TransactionBackend.class);
        transaction = new Transaction();
        transaction.productName = "Name";
        transaction.type = TransactionType.BUY;
        transaction.count = 3;
        transaction.price = BigDecimal.valueOf(20.8);
        reporter = new NewTransactionReporter(transactionBackend);
    }

    @Test
    public void transactionIsSendToBackend(){
        getNewTransactionResult();

        verify(transactionBackend).sendTransaction(transaction);
    }

    @Test
    public void reportTransactionReturnsSuccess() {
        NewTransactionResult result = getNewTransactionResult();

        Assert.assertTrue(result.isSuccess);
    }

    @Test
    public void transactionIdIsEqualToIdReturnedByBackend() {
        int expectedId = 2;
        setBackendToReturnId(expectedId);

        NewTransactionResult result = getNewTransactionResult();

        Assert.assertEquals(expectedId, result.id);
    }

    @Test
    public void whenTransactionIsNotAcceptedReturnsNotSuccess(){
        int expectedId = -1;
        setBackendToReturnId(expectedId);

        NewTransactionResult result = getNewTransactionResult();

        Assert.assertFalse(result.isSuccess);
    }

    private void setBackendToReturnId(int expectedId) {
        when(transactionBackend.sendTransaction(transaction)).thenReturn(expectedId);
    }

    private NewTransactionResult getNewTransactionResult() {
        return reporter.reportNewTransaction(transaction);
    }

    @Test
    public void whenTransactionIdIsInvalidExceptionIsThrown(){
        int expectedId = -2;
        setBackendToReturnId(expectedId);

        try {
            getNewTransactionResult();
            Assert.fail("Exception was not thrown.");
        }catch (InvalidTransactionIdException exception){

        }
    }

    @Test
    public void checkThatTransactionArgumentsAreCorrectlySendFromBackendToReporter() {
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        getNewTransactionResult();
        verify(transactionBackend).sendTransaction(transactionCaptor.capture());
        Transaction actualTransaction = transactionCaptor.getValue();

        Assert.assertEquals(transaction.productName, actualTransaction.productName);
        Assert.assertEquals(transaction.count, actualTransaction.count);
        Assert.assertEquals(transaction.type, actualTransaction.type);
        Assert.assertEquals(transaction.price, actualTransaction.price);
    }

}