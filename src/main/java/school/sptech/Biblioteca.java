package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.*;

public class Biblioteca {

    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {

    }

    public void adicionarLivro(Livro livro) throws ArgumentoInvalidoException {
        if (livro == null) {
            throw new ArgumentoInvalidoException("O livro não pode ser nulo.");
        }

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new ArgumentoInvalidoException("O título do livro não pode ser nulo ou vazio.");
        }

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new ArgumentoInvalidoException("O autor do livro não pode ser nulo ou vazio.");
        }

        if (livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException("A data de publicação do livro não pode ser nula.");
        }
        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) throws LivroNaoEncontradoException, ArgumentoInvalidoException {


        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("O título não pode ser nulo, vazio ou conter apenas espaços em branco.");
        }

        Livro livroParaRemover = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroParaRemover = livro;
                break;
            }
        }

        if (livroParaRemover == null) {
            throw new LivroNaoEncontradoException("O livro com o título '" + titulo + "' não foi encontrado.");
        }
        livros.remove(livroParaRemover);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank() || titulo.isEmpty()) {
            throw new ArgumentoInvalidoException("O Titulo não pode ser nulo ou vazio!");
        }

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("Erro ao encontrar o livro com o título: " + titulo);
    }



    public Integer contarLivros() {

        Integer contagem = 0;

        for (Livro livro : livros) {
            contagem++;
        }

        return contagem;
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosAteAno = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getDataPublicacao().getYear() <= ano) {
                livrosAteAno.add(livro);
            }
        }

        return livrosAteAno;
    }



    public List<Livro> retornarTopCincoLivros() {
        List<Livro> livrosOrdenados = new ArrayList<>(livros);

        Collections.sort(livrosOrdenados, new Comparator<Livro>() {
            @Override
            public int compare(Livro livro1, Livro livro2) {
                return Double.compare(livro2.calcularMediaAvaliacoes(), livro1.calcularMediaAvaliacoes());
            }
        });

        return livrosOrdenados.size() <= 5 ? livrosOrdenados : livrosOrdenados.subList(0, 5);
    }




    /*
    Getter e Setter
     */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
