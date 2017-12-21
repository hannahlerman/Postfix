package stack;


/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	LLNode<T> head;
	int size;
	
	public LinkedStack(){
		head = null;
		size = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(head == null){
			throw new StackUnderflowException();
		}
		
		LLNode<T> temp = new LLNode<T>(head.getData());
		head = head.getNext();
		size--;
		return temp.getData();
			
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(head == null){
			throw new StackUnderflowException();
		}
		return head.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
    	if(head == null){
    		return true;
    	}

    	return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
    // TODO
    return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> nn = new LLNode<T>(elem);
			
		nn.setNext(head);
		head = nn;
		size++;
	}

}
