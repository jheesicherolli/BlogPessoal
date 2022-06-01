package org.generation.blogPessoal.model;
//tudo que será mandado para o banco de dados
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
// se trata de uma entidade que será mapeada como tabela no banco de dados
@Table(name =  "tb_postagem")
/*com o @Table, estamos nos comunicando com o banco de dados e pedindo para que crie
tabelas e dando atributos a elas*/
public class Postagem {
    
	@Id
	//indica a chave primária,ou seja o id será a primary key
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//equivale ao auto incremente do mysql
	private long id;
	
	@NotNull 
	//para não aceitar nada vazio;
	private String titulo;
	
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	//indica pata o jpa hibernate que estamos trabalhando com tempo
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	

	public long getId() {
		return id;
		/*gets e sets manipulam os dados*/
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	
	
}
