package kaa.soft.cookbook;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * Created by user on 03.05.17.
 */
public class TransactionBean implements PlatformTransactionManager {
    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        return null;
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {

    }

    @Override
    public void rollback(TransactionStatus transactionStatus) throws TransactionException {

    }
}
