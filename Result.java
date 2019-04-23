package cis233.a1;
/**
 *
 * @author R
 */
public interface Result <AnyType extends Comparable<? super AnyType>>
{
    AnyType mode();
    int count();
}
