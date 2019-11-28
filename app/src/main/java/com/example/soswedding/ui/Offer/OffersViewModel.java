package com.example.soswedding.ui.Offer;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Singleton;
import static com.example.soswedding.service.BidService.acceptBid;
import static com.example.soswedding.service.BidService.declineBid;
public class OffersViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public OffersViewModel() {
    }
    public void acceptBidModel(Context context,long requestID, long bidId) {
        acceptBid(context, requestID, bidId, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("My App", "SUCCESS!");
            }
        });
    }

    public void declineBidModel(Context context, long bidId){
    declineBid(context, bidId, new VolleyCallback() {
        @Override
        public void onSuccess(String result) {
            Log.e("My App", "SUCCESS!");
        }
    });
}
}