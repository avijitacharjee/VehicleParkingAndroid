package com.avijit.vehicleparking;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentPaymentBinding;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 5:01 AM.
 * Email: avijitach@gmail.com.
 */
public class PaymentFragment extends Fragment {
    FragmentPaymentBinding binding ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 500f);
        //valueAnimator.setRepeatCount(1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        // animate over the course of 700 milliseconds
        valueAnimator.setDuration(1000);
        // define how to update the view at each "step" of the animation
        valueAnimator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            binding.balanceText.setRotationX(progress);
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                binding.balanceText.setRotationX(0);
                ObjectAnimator.ofFloat(binding.balanceText, "textSize", 1,18).setDuration(650).start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        valueAnimator.start();
        Objects.requireNonNull(binding.balanceText).setText(String.format(Locale.ENGLISH,"%s%d",
                getActivity().getString(R.string.balance_text),
                getActivity().getSharedPreferences("s", Context.MODE_PRIVATE).getInt("balance", 0)));
    }
}
