'use strict';

const limparFormulario = (endereco) =>{
    document.getElementById('endereco').value = '';
    document.getElementById('estado').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('bairro').value = '';
}


const preencherFormulario = (endereco) =>{
    document.getElementById('endereco').value = endereco.uf;
    document.getElementById('bairro').value = endereco.logradouro;
    document.getElementById('cidade').value = endereco.bairro;
    document.getElementById('estado').value = endereco.localidade;
}


const eNumero = (numero) => /^[0-9]+$/.test(numero);


const cepValido = (cep) => cep.length == 8 && eNumero(cep); 

const pesquisarCep = async() => {
    limparFormulario();
    


    const cep = document.getElementById('cep').value.replace("-","");
    const url = `https://viacep.com.br/ws/${cep}/json/`;
    if (cepValido(cep)){
        const dados = await fetch(url);
        const endereco = await dados.json();
        if (endereco.hasOwnProperty('erro')){
            document.getElementById('endereco').value = 'CEP não encontrado!';
        }else {
            preencherFormulario(endereco);
        }
    }else{
        document.getElementById('endereco').value = 'CEP errado mano(a)!';
    }
     
}

document.getElementById('cep')
        .addEventListener('focusout',pesquisarCep);