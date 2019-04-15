package model.data_structures;

/**
 * Clase de lista doblemente encadenada genérica.
 * @param <T> Objeto genérico dentro de la lista.
 */
public class DoubleLinkedList<T extends Comparable<T>> extends AbstractList<T>
{
	//--------------------------------
	//ATRIBUTOS
	//--------------------------------
	
	/**
	 * 
	 */
	private DoubleNode<T> actualNode;
	private DoubleNode<T> lastNode;
	
	//--------------------------------
	//CONSTRUCTORES
	//--------------------------------
	
	 /**
     * Construye una lista vacia
     * <b>post:< /b> se ha inicializado el primer nodo en null
     */
	public DoubleLinkedList() 
	{
		firstNode = null;
		actualNode = null;
		lastNode = null;
		size = 0;
	}
	
	/**
     * Se construye una nueva lista cuyo primer nodo  guardarï¿½ al elemento que llega por parï¿½mentro
     * @param nFirst el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
	public DoubleLinkedList(T nFirst)
	{
		if(nFirst == null)
		{
			throw new NullPointerException("Se recibe un elemento nulo");
		}
		firstNode = new DoubleNode<T>(nFirst);
		lastNode = (DoubleNode<T>) firstNode;
		actualNode = null;
		size = 1;
	}
	
	//--------------------------------
	//METODOS
	//--------------------------------
	
	/**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id.
     * Se actualiza la cantidad de elementos.
     * @param e el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo.
     */
	@Override
	public boolean add(T element) 
	{
		boolean agregado = false;
		if(element == null)
		{
			throw new NullPointerException();
		}
		if(!this.contains(element))
		{
			if(this.isEmpty())
			{
				this.firstNode = new DoubleNode<T>(element);
				this.lastNode = (DoubleNode<T>) this.firstNode; 
				this.size++;
				agregado = true;
			}
			else
			{				
				DoubleNode<T> nuevo = new DoubleNode<T>(element);
				nuevo.changeNext(lastNode.getNextNode());
				lastNode.changeNext(nuevo);
				nuevo.changePrevious(lastNode);
				lastNode = nuevo;
				agregado = true;
				this.size++;
			}
		}
		return agregado;		
	}

	/**
	 * Elimina un elemento de la lista.
	 * @return True si pudo eliminar el elemento. False de lo contrario.
	 */
	@Override
	public boolean delete(T o) 
	{
		boolean elimino = false;
		boolean encontro = false;
		
		DoubleNode<T> n = (DoubleNode<T>) this.firstNode;
		while(n != null && ! encontro)
		{
			if(n.getElement().compareTo((T)o) == 0)
			{
				encontro = true;
			}
			else
			{		
				n = (DoubleNode<T>) n.getNextNode();
			}
		}
		
		if(encontro)
		{
			DoubleNode<T> anterior = n.getPrevious();
			if(anterior == null)
			{
				this.firstNode = n.getNextNode();
				if(firstNode != null)
				{
					((DoubleNode<T>)this.firstNode).changePrevious(null);
				}
			}
			else 
			{
				if (n.getNextNode() != null)
				{
					anterior.changeNext(n.getNextNode());
					((DoubleNode<T>)n.getNextNode()).changePrevious(anterior);
				}
				else
				{
					anterior.changeNext(n.getNextNode());
				}
			}
			elimino = true;
			this.size--;
		}
		return elimino;	
	}

	/**
	 * Da el elemento que se busca dentro de la lista.
	 * @return T Elemento que se quiere conseguir de la lista.
	 */
	@Override
	public T get(T o) 
	{
		DoubleNode<T> n = (DoubleNode<T>)this.firstNode;
		T searched = null;
		boolean found = false;
		if(n != null)
		{
			while(n != null && !found)
			{
				if(n.getElement().compareTo((T)o) == 0)
				{
					found = true;
					searched = n.getElement();
				}
				else
				{
					n = (DoubleNode<T>) n.getNextNode();
				}
			}
		}
		return searched;
	}

	/**
	 * Permite que el nodoActual se convierta en el primer nodo.
	 */
	@Override
	public void listing() 
	{
		actualNode = (DoubleNode<T>) this.firstNode;
	}
	
	/**
	 * Da el elemento del nodoActual.
	 * @return T Elemento del nodoActual.
	 */
	@Override
	public T getCurrent() 
	{
		return actualNode.getElement();
	}
	
	/**
	 * Retorna el nodoActual.
	 * @return nodoActual
	 */
	public DoubleNode<T> getActualNode()
	{
		return actualNode;
	}
	
	/**
	 * Busca si hay un siguiente del nodoActual.
	 * @return True si hay un siguiente del nodoActual. False de lo contrario.
	 */
	@Override
	public boolean next() 
	{
		boolean exists = false;
		if(actualNode != null)
		{
			exists = true;
			actualNode = (DoubleNode<T>) actualNode.getNextNode();
		}
		return exists;
	}
	
	/**
	 * Busca si hay un anterior del nodoActual.
	 * @return True si hay un anterior del nodoActual. False de lo contrario.
	 */
	public boolean previous()
	{
		boolean exists = false;
		if(actualNode != null)
		{
			exists = true;
			actualNode = actualNode.getPrevious();
		}
		return exists;
	}

	/**
	 * Da el iterador de la lista comenzando en el primer nodo.
	 * @return Iterador de la lista.
	 */
	public IteratorList<T> iterator() 
	{
		return new IteratorList<T>(firstNode);
	}

}
