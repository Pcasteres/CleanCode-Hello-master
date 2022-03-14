package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToHelloState;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;

public class ByePresenter implements ByeContract.Presenter {

    public static String TAG = ByePresenter.class.getSimpleName();

    private WeakReference<ByeContract.View> view;
    private ByeState state;
    private ByeContract.Model model;
    private AppMediator mediator;

    public ByePresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getByeState();
    }

    @Override
    public void onResumeCalled() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        HelloToByeState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
           // model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            state.byeMessage = savedState.message;
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().displayByeData(state);
    }

    private HelloToByeState getStateFromNextScreen() {
        return mediator.getHelloToByeState();
    }
    private void startByeMessageAsyncTask() {
        String data = model.getByeMessage();
        state.byeMessage = data;
        view.get().displayByeData(state);
    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }
/*
    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }*/



    /*private void passStateToNextScreen(ByeToHelloState state) {
        mediator.setNextByeScreenState(state);
    }

    private void passStateToPreviousScreen(ByeToPreviousState state) {
        mediator.setPreviousByeScreenState(state);
    }

    private PreviousToByeState getStateFromPreviousScreen() {
        return mediator.getPreviousByeScreenState();
    }*/

    //TODO
    @Override
    public void sayByeButtonClicked() {
        state.byeMessage = "?";
        view.get().displayByeData(state);
        startByeMessageAsyncTask();
    }

    //TODO

    @Override
    public void goHelloButtonClicked() {
        ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
        passDataToHelloScreen(newState);
        //se le dice a la activity que se acabe
        view.get().finishView();
       // navigateToHelloScreen();

    }

   /* TODO
   private void navigateToHelloScreen() {


    }*/

    //TODO
    private void passDataToHelloScreen(ByeToHelloState newState) {
        mediator.setByeToHelloState(newState);
    }

    @Override
    public void injectView(WeakReference<ByeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ByeContract.Model model) {
        this.model = model;
    }

}
