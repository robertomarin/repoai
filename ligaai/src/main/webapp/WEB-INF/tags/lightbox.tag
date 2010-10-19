<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<div id="subscribe">
	<div class="content">
		<div class="message hide"></div>
		<form id="u_criar">
			<strong class="ligaStyle">registra<span class="baseColor">.</span>ai</strong>
			<p>Já tem uma conta? <span id="login" class="formAction baseColor">Faça login</span></p>
			<ul>
				<li><label>Nome de usuário</label></li>
				<li><input id="name" type="text" /></li>
				<li><label>E-mail</label></li>
				<li><input id="email" type="text" /></li>
				<li><label>Senha</label></li>
				<li><input id="password" type="password" /></li>
				<li id="terms">
					<code>
						Terms of Service
						These Terms of Service (“Terms”) govern your access to and use of the services and Twitter’s websites (the “Services”), and any information, text, graphics, or other materials uploaded, downloaded or appearing on the Services (collectively referred to as “Content”). Your access to and use of the Services is conditioned on your acceptance of and compliance with these Terms. By accessing or using the Services you agree to be bound by these Terms.
	
						Basic Terms
	
						You are responsible for your use of the Services, for any content you post to the Services, and for any consequences thereof. The Content you submit, post, or display will be able to be viewed by other users of the Services and through third party services and websites (go to the account settings page to control who sees your Content). You should only provide Content that you are comfortable sharing with others under these Terms.
					</code>
				</li>
				<li>Ao clicar em registrar, estou concordando com os termos de uso</li>
				<li><input type="submit" value="registra.ai" /></li>
			</ul>
		</form>
		<form id="u_entrar" class="hide">
			<strong class="ligaStyle">loga<span class="baseColor">.</span>ai</strong>
			<p>Esqueceu sua senha? <span id="forgotPass" class="formAction baseColor">Recupere agora!</span></p>
			<ul>
				<li><label>E-mail</label></li>
				<li><input id="email" type="text" /></li>
				<li><label>Senha</label></li>
				<li><input id="password" type="password" /></li>
				<li><input type="submit" value="loga.ai" /></li>
			</ul>
		</form>
		<form id="resetForm" class="hide">
			<strong class="ligaStyle">lembra<span class="baseColor">.</span>ai</strong>
			<p>Nós enviaremos um e-mail de como criar uma nova senha</p>
			<ul>
				<li><label>Nome de usuário</label></li>
				<li><input type="text" /></li>
				<li><input type="submit" value="envia.ai" /></li>
			</ul>
		</form>
	</div>
</div>