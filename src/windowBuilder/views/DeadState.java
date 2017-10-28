package windowBuilder.views;

public class DeadState implements GameState{
	public void acao(Context context){
        context.setState(this);
     }

     public boolean state(){
        return false;
     }
}
