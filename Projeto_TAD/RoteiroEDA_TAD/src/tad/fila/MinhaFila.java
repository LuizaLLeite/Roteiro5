package tad.fila;


public class MinhaFila implements FilaIF<Integer> {

    private int tamanho = 10;

    private int cauda = 1;
    private int cabeca = 0;

    private Integer[] meusDados = null;


    public MinhaFila(int tamanhoInicial) {
        tamanho = tamanhoInicial;
        meusDados = new Integer[tamanho];
    }

    public MinhaFila() {
        meusDados = new Integer[tamanho];
    }

    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        if (isFull()) {
            throw new FilaCheiaException();
        }
    
        cauda = (cauda + 1) % tamanho;
        meusDados[cauda] = item;
        System.out.println(meusDados[cauda]);
    
        
        if (cabeca == 0) {
            cabeca = cauda; 
        }
    }
    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }

        Integer valorDesenfileirado = meusDados[cabeca];
        meusDados[cabeca] = null;

        cabeca = (cabeca + 1) % tamanho;

        return valorDesenfileirado;
    }

    @Override
    public Integer verificarCauda() {
        
        System.out.println(meusDados[cauda]);
        return meusDados[cauda];
    }

    @Override
    public Integer verificarCabeca() {
       
        System.out.println(cabeca);
        return meusDados[cabeca];
    }

    @Override
    public boolean isEmpty() {
        if(meusDados[cabeca] == null){
            return true;
        }
       else{
        return false;
       }
       
    }

    @Override
    public boolean isFull() {
        return (cauda + 1) % tamanho == cabeca;
    }
}
