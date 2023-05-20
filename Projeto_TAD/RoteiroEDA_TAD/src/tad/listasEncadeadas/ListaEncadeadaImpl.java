package tad.listasEncadeadas;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	private NodoListaEncadeada<T> cabeca;
	private int tamanho;

	public ListaEncadeadaImpl() {
		this.cabeca = null;
		this.tamanho = 0;
	}

	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}

	@Override
	public int size() {
		return tamanho;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> atual = cabeca;
		while (atual != null) {
			if (atual.getChave().equals(chave)) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<>(chave);

		if (isEmpty()) {
			cabeca = novoNodo;
		} else {
			NodoListaEncadeada<T> atual = cabeca;
			while (atual.getProximo() != null) {
				atual = atual.getProximo();
			}
			atual.setProximo(novoNodo);
		}

		tamanho++;
		
	}

	@Override
	public void insert(T chave, int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Índice inválido!");
		}
	
		NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<>(chave);
	
		if (index == 0) {
			novoNodo.setProximo(cabeca);
			cabeca = novoNodo;
		} else {
			NodoListaEncadeada<T> anterior = getNode(index - 1);
			novoNodo.setProximo(anterior.getProximo());
			anterior.setProximo(novoNodo);
		}
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) {
		try {
			if (isEmpty()) {
				throw new ListaVaziaException("A lista está vazia. Não é possível remover elementos.");
			 }
		 
			 if (cabeca.getChave().equals(chave)) {
				 NodoListaEncadeada<T> removido = cabeca;
				 cabeca = cabeca.getProximo();
				 removido.setProximo(null);
				 tamanho--;
				 return removido;
			 }
		 
			 NodoListaEncadeada<T> atual = cabeca;
			 NodoListaEncadeada<T> anterior = null;
		 
			 while (atual != null) {
				 if (atual.getChave().equals(chave)) {
					 anterior.setProximo(atual.getProximo());
					 atual.setProximo(null);
					 tamanho--;
					 return atual;
				 }
				 anterior = atual;
				 atual = atual.getProximo();
			 }
		} catch (ListaVaziaException e) {
			
		}
	
		return null;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder sb = new StringBuilder();

		NodoListaEncadeada<T> atual = cabeca;
		while (atual != null) {
			sb.append(atual.getChave());
			if (atual.getProximo() != null) {
				sb.append(", ");
			}
			atual = atual.getProximo();
		}

		return sb.toString();
	}
	
	@Override
	public String imprimeInverso() {
		if (cabeca == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		NodoListaEncadeada<T> atual = cabeca;
		while (atual != null) {
			sb.insert(0, atual.getChave().toString() + ", ");
			atual = atual.getProximo();
		}
		sb.setLength(sb.length() - 2); // Remove the trailing comma and space
		return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> atual = cabeca;
		while (atual != null && !atual.getChave().equals(chave)) {
			atual = atual.getProximo();
		}
		if (atual != null && atual.getProximo() != null) {
			return atual.getProximo();
		}
		return null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		if (cabeca == null) {
			return null;
		}
		NodoListaEncadeada<T> atual = cabeca;
		NodoListaEncadeada<T> anterior = null;
	
		while (atual != null && !atual.getChave().equals(chave)) {
			anterior = atual;
			atual = atual.getProximo();
		}
	
		if (atual != null && anterior != null) {
			return anterior;
		}
	
		return null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (cabeca == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, size());
		NodoListaEncadeada<T> atual = cabeca;
		int index = 0;
		while (atual != null) {
			array[index] = atual.getChave();
			atual = atual.getProximo();
			index++;
		}
		return array;
	}
	private NodoListaEncadeada<T> getNode(int index) {
		if (index < 0 || index >= tamanho) {
			throw new IndexOutOfBoundsException();
		}
		NodoListaEncadeada<T> atual = cabeca;
		for (int i = 0; i < index; i++) {
			atual = atual.getProximo();
		}
		return atual;
	}
}