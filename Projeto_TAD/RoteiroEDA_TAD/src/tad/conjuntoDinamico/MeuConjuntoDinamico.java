package tad.conjuntoDinamico;

import java.util.NoSuchElementException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	
	private Integer[] meusDados = null;
	private int posInsercao = 0;
	
	@Override
	public void inserir(Integer item) { if (item == null) {
		throw new IllegalArgumentException("O item não pode ser nulo.");
	}
	
	if (meusDados == null) {
		meusDados = new Integer[1];
		meusDados[0] = item;
	} else {
		if (posInsercao >= meusDados.length) {
			Integer[] arrayMaior = aumentarArray();
			meusDados = arrayMaior;
		}
		meusDados[posInsercao] = item;
	}
	posInsercao++;
		
	}
	
	private Integer[] aumentarArray() {
		int novoTamanho = meusDados.length * 2;
        Integer[] arrayMaior = new Integer[novoTamanho];
        System.arraycopy(meusDados, 0, arrayMaior, 0, meusDados.length);
        return arrayMaior;
	}

	@Override
	public Integer remover(Integer item) {
		if (item == null) {
            throw new UnsupportedOperationException("O item não pode ser nulo.");
        }
        
        if (meusDados == null) {
			throw new NoSuchElementException("Conjunto vazio, não é possível remover.");
        }

        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removedItem = meusDados[i];
                posInsercao--;
                meusDados[i] = meusDados[posInsercao];
                meusDados[posInsercao] = null;
                return removedItem;
            }
        }

		throw new NoSuchElementException("Item não encontrado no conjunto.");
		
	}

	@Override
	public Integer predecessor(Integer item) {
		if (item == null) {
			throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        
        if (meusDados == null) {
			throw new UnsupportedOperationException("Conjunto vazio, não é possível obter o predecessor.");
        }

        Integer predecessor = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return predecessor;
            }
            predecessor = meusDados[i];
        }

        throw new UnsupportedOperationException("Item não encontrado no conjunto.");
	}

	@Override
	public Integer sucessor(Integer item) {
		if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        
        if (meusDados == null) {
			throw new UnsupportedOperationException("Conjunto vazio, não é possível obter sucessor.");
        }

        boolean found = false;
        for (int i = 0; i < posInsercao; i++) {
            if (found)
                return meusDados[i];
            if (meusDados[i].equals(item))
                found = true;
        }

        return null;
	}

	@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) {
		if (item == null) {
			throw new IllegalArgumentException("O item não pode ser nulo.");
		}
		
		if (meusDados == null) {
			return null;
		}
	
		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				return item;
			}
		}
	
		throw new NoSuchElementException("Item não encontrado no conjunto.");
	}

	@Override
	public Integer minimum() {
		if (meusDados == null || posInsercao == 0) {
            throw new UnsupportedOperationException("Conjunto Vazio");
        }

        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min)
                min = meusDados[i];
        }

        return min;
	}

	@Override
	public Integer maximum() {
		if (meusDados == null || posInsercao == 0) {
            throw new UnsupportedOperationException("Conjunto Vazio");
        }

        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max)
                max = meusDados[i];
        }

        return max;
    }
}