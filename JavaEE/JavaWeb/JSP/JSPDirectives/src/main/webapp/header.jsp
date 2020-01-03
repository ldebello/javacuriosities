<%!
    int pageCount = 0;

    public void addCount() {
        pageCount++;
    }
%>
<% addCount(); %>

<h2>Ejemplo</h2>
<p>Contador de visitas <%= pageCount %>
</p>
<br/>
<br/>