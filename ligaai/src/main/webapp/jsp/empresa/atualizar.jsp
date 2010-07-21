<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<pre>

	Empresa atualizada com sucesso!
	
	Detalhes da Empresa
	Empresa: ${empresa.id}
	Nome: ${empresa.nome}
	Data Criacao: <fmt:formatDate value="${empresa.dataCriacao.time}" pattern="dd/MM/yyyy HH:mm:ss" />
</pre>

