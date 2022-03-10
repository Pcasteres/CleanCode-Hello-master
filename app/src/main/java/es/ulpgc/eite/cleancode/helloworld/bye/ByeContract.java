package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

public interface ByeContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayByeData(ByeViewModel viewModel);

        void navigateToNextScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        //void onStart();

        //void onRestart();

        void onBackPressed();

        //void onPause();

        //void onDestroy();

        void sayByeButtonClicked();

        void goHelloButtonClicked();
    }

    interface Model {
        String getByeMessage();
    }

}
