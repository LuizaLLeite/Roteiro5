package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 10;
	private Integer[] meusDados = null;
	private int topo = -1;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		meusDados = new Integer[tamanho];
	}

	public MinhaPilha() {
		meusDados = new Integer[tamanho];
	}

	@Override
public void empilhar(Integer item) throws PilhaCheiaException {
    if (topo == tamanho - 1) {
        throw new PilhaCheiaException();
    }
    
    topo = (topo + 1) % tamanho;
    meusDados[topo] = item;
}
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException("A pilha está vazia");
		}
		Integer itemDesempilhado = meusDados[topo];
		meusDados[topo] = null;
		topo--;
		return itemDesempilhado;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[topo];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		if (k <= 0 || k > tamanho) {
			throw new IllegalArgumentException("O valor de k deve ser positivo e menor ou igual ao tamanho da pilha");
		}

		PilhaIF<Integer> subPilha = new MinhaPilha(k);
		for (int i = topo - 1; i >= topo - k; i--) {
			try {
				subPilha.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				e.printStackTrace();
			}
		}
		return subPilha;
	}


	@Override
	public boolean isEmpty() {
		if( topo == -1){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		MinhaPilha outraPilha = (MinhaPilha) obj;
		if (topo != outraPilha.topo) {
			return false;
		}
		for (int i = 0; i <= topo; i++) {
			if (!meusDados[i].equals(outraPilha.meusDados[i])) {
				return false;
			}
		}
		return true;
	}

}