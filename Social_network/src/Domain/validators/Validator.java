package Domain.validators;

public interface Validator<T> {

    void validate(T entity) throws ValidationExceptions;
}
