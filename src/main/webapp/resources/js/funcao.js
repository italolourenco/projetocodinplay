function ir_telaCadastro(){
	window.location = "tela_cadastro.html";
}

function ir_telaLogin(){
	window.location = "index.html";
}

function ir_telaPrincipal(){
	window.location = "paginas/tela_principal.html";
}

function ir_progressaoUsuario(){
	window.location = "tela_progressaoUsuario.html";
}

function ir_telaRanking(){
}

function ir_telaSobre(){
	window.location = "tela_sobre.html";
}

function ir_telaContato(){
	window.location = "tela_contato.html";
}

function ir_telaDesafio(){
	window.location = "tela_resolucaoDesafioSemanal.html"
}

nome="";

function fazerLogin() {
	var login = document.getElementById("login").value;
	var senha = document.getElementById("senha").value;
	
    if ((login == "admin@codinplay.com" && senha == "123"))    
    {
		logado = "Administrador";
        criarlocalStorage(logado);
		window.location = "paginas/tela_principalAdmin.html";
		return true;		
    } else if (login=="igor@codinplay.com" && senha == "123"){
    	logado = "Igor Soares";
        criarlocalStorage(logado);
    	window.location = "paginas/tela_principal.html";
        return true;
    }else if (login=="italo@codinplay.com" && senha == "123"){
        logado = "OLÁ, ITALO";
        criarlocalStorage(logado);
        window.location = "paginas/tela_principal.html";
        return true;
    }
    else{
    	alert("usuário ou senha incorretos");
		return false;	
    }
}

function verificaEmail(email) {
    var er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
    if (er.exec(email)) 
    {
        return true;
    } else 
    {
    	alert('Email inválido');
        return false;
    }
}
function verificaEmail2(email) {
    var er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
    if (er.exec(email)) 
    {
        return true;
    } else 
    {
        return false;
    }
}

function validarSenhaIgual(){
	var senha = document.getElementById("senha").value;
	var con_senha = document.getElementById("con_senha").value;
	if (senha != con_senha) {
		alert('Senhas diferentes');
		return false;
	}else{
		return true;
	}
}

function validaData(valor) {
    var date = valor;
    var ardt = new Array;
    var ExpReg = new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
    ardt = date.split("/");
    erro = false;

    if (date.search(ExpReg) == -1) {
        erro = true;
    } else if (((ardt[1] == 4) || (ardt[1] == 6) || (ardt[1] == 9) || (ardt[1] == 11)) && (ardt[0] > 30)) {
        erro = true;
    } else if (ardt[1] == 2) {
        if ((ardt[0] > 28) && ((ardt[2] % 4) != 0)) {
            erro = true;
        }
        if ((ardt[0] > 29) && ((ardt[2] % 4) == 0)) {
            erro = true;
        }
    }
    if (erro) {
        alert('Data inválida');
        return false;
    }
    return true;
}


function validarFormInstituicao()
{
    var cont=0;
    var string = "";
    var nome = document.getElementById("nome").value;
    var abreviacao = document.getElementById("abreviacao").value;
    var cidade = document.getElementById("cidade").value;
    var estado = document.getElementById("estado").value;
    var telefone = document.getElementById("telefone").value;
    var site = document.getElementById("site").value;
   
    if(nome == ""){
        cont++;
        string = string + " Nome";
    }
    if(abreviacao == ""){
        cont++;
        string = string + " Abreviação";
        
    }
    if(cidade == ""){
        cont++;
        string = string + " Cidade";
    }
    if(estado == ""){
        cont++;
        string = string + " Estado";
    }
    if(telefone == ""){
        cont++;
        string = string + " Telefone";
    }
    if(site == ""){
        cont++;
        string = string + " Site";
    }
    if(cont>0){
        var resultado ;
        alert("Favor Preencher o(s) seguinte(s) campo(s): " + string);
        return false;
    }
    return true;
}
function verificaRadioButton(id)
{
    var el = id;
    for (var i=0; i<el.length; i++) {
        if (el[i].checked) {
            return true;
        }
    }
    return false;
}   
function verificaRadioButtonJanela(id)
{
    var el = id;
    for (var i=0; i<el.length; i++) {
        if (el[i].checked) {
            return true;
        }
    }
    alert("Favor selecionar uma resposta!");
    return false;
} 

function validarFormCadastroTarefa()
{
    var cont=0;
    var string = "";
    var optradio = verificaRadioButton(document.getElementsByName("optradio"));
    var optTarefa = verificaRadioButton(document.getElementsByName("optTarefa"));
    var descricao = document.getElementById("descricao").value;
    var nivel = document.getElementById("nivel").value;
    var letraA = document.getElementById("letraA").value;
    var letraB = document.getElementById("letraB").value;
    var letraC = document.getElementById("letraC").value;
    var letraD = document.getElementById("letraD").value;
    var letraE = document.getElementById("letraE").value;
    if(!optTarefa){
        cont++;
        string = string + " Tarefa";
    }
    if(descricao == ""){
        cont++;
        string = string + " Descrição";
    }
    if(nivel == ""){
        cont++;
        string = string + " Nivel";
    }
    if(!optradio){
        cont++;
        string = string + " Resposta";
    }
    if(letraA == ""){
        cont++;
        string = string + " letraA";
    }
    if(letraB== ""){
        cont++;
        string = string + " letraB";
    }
    if(letraC == ""){
        cont++;
        string = string + " letraC";
    }
    if(letraD == ""){
        cont++;
        string = string + " letraD";
    }
    if(letraE == ""){
        cont++;
        string = string + " letraE";
    }

    if(cont>0){
        var resultado ;
        alert("Favor Preencher o(s) seguinte(s) campo(s): " + string);
        return false;
    }
    return true;
}

function validarEditarCadastrar(){
    var cont=0;
    var string = "";
    var nome = document.getElementById("nome").value;
    var instituicao = document.getElementById("instituicao").value;
    var sexo = document.getElementById("sexo").value;
    var data = document.getElementById("data").value;
    var email = verificaEmail2(document.getElementById("email"));
    var telefone = document.getElementById("telefone").value;
    var senha = document.getElementById("senha").value;
    var con_senha = document.getElementById("con_senha").value;
    
    if(nome == ""){
        cont++;
        string = string + " Nome";
    }
    if(instituicao == ""){
        cont++;
        string = string + " Instituição";
    }

    if(sexo == ""){
        cont++;
        string = string + " Sexo";
    }

    if(data == ""){
        cont++;
        string = string + " Data";
    }
    
    if(!email){
        cont++;
        string = string + " Email";
    }
    if(telefone == ""){
        cont++;
        string = string + " Telefone";
    }
    if(senha == ""){
        cont++;
        string = string + " Senha";
    }  
    
    if(con_senha == ""){
        cont++;
        string = string + " Contra Senha";
    }  

    if(cont>0){
        var resultado ;
        alert("Favor Preencher o(s) seguinte(s) campo(s): " + string);
        return false;
    }
    return true;
}

function validaContato(){
    var cont=0;
    var string = "";
    var email = document.getElementById("email").value;
    var dicas = document.getElementById("dicas").value;
     if(email == ""){
        cont++;
        string = string + " Email";
    }  
    
    if(dicas == ""){
        cont++;
        string = string + " Dicas";
    }  
    if(cont>0){
        var resultado ;
        alert("Favor Preencher o(s) seguinte(s) campo(s): " + string);
        return false;
    }
    return true;
}
//recurso html5
function criarlocalStorage(logado){
    window.localStorage.setItem('nome', logado);
}
//recurso html5
function inserirLog(){
    document.getElementById('ID_bottonSair').innerHTML = window.localStorage.getItem('nome') + "  | SAIR";
}