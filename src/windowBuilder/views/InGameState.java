package windowBuilder.views;

public class InGameState implements GameState{
	 public void acao(Context context){
	        context.setState(this);
	     }

	     public boolean state(){
	        return true;
	     }
}
