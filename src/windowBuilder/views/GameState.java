package windowBuilder.views;

public interface GameState {
	public static final boolean EM_JOGO = true;
	public static final boolean PARADO = false;
	public void acao(Context context);
	public boolean state();
}
