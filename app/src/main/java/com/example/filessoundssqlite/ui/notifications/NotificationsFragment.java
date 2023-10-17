package com.example.filessoundssqlite.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.filessoundssqlite.R;
import com.example.filessoundssqlite.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    private ImageView img_v1;
    private LinearLayout ly_1;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        img_v1 = binding.imgV1;
        ly_1 = binding.ly1;

        for (int i = 0; i < ly_1.getChildCount(); i++) {
            View child = ly_1.getChildAt(i);
            if (child instanceof ImageButton) {
                final ImageButton imageButton = (ImageButton) child;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contentDescription = imageButton.getContentDescription().toString();
                        selected(contentDescription);

                    }
                });
            }
        }

        binding.btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });

        return root;
    }

    public void hide() {
        binding.btnHide.setVisibility(View.INVISIBLE);
        img_v1.setVisibility(View.VISIBLE);
    }

    public void selected(String contentDescription) {
        switch (contentDescription) {
            case "Sandia":
                Toast.makeText(getContext(), getString(R.string.sandia_description), Toast.LENGTH_SHORT).show();
                break;
            case "Pina":
                Toast.makeText(getContext(), getString(R.string.pina_description), Toast.LENGTH_SHORT).show();
                break;
            case "Pera":
                Toast.makeText(getContext(), getString(R.string.pera_description), Toast.LENGTH_SHORT).show();
                break;
            case "Melon":
                Toast.makeText(getContext(), getString(R.string.melon_description), Toast.LENGTH_SHORT).show();
                break;
            case "Manzanas":
                Toast.makeText(getContext(), getString(R.string.manzanas_description), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(), "Fruta desconocida", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}