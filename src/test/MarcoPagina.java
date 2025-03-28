package test;
public class MarcoPagina {
  private Pagina pagina;
  private boolean referenced = false;
  private boolean modified = false;

  public void setPagina(Pagina pagina) { this.pagina = pagina; }
  public Pagina getPagina() { return pagina; }
  public void setReferenced(boolean r) { this.referenced = r; }
  public void setModified(boolean m) { this.modified = m; }
}
