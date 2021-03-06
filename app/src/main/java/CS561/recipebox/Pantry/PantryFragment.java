package CS561.recipebox.Pantry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import CS561.recipebox.R;
import CS561.recipebox.ui.gallery.GalleryViewModel;

public class PantryFragment extends Fragment
{
    private GalleryViewModel galleryViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PantryFragment newInstance(int index)
    {
        PantryFragment fragment = new PantryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);
        return root;
    }

}
