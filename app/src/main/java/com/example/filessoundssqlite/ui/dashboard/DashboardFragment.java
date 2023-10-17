package com.example.filessoundssqlite.ui.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.filessoundssqlite.databinding.FragmentDashboardBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DashboardFragment extends Fragment {
    private EditText txt_file;
    private String fileName = "bitacora.txt";
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_file = (EditText) binding.txtFile;
        String files[] = getActivity().fileList();
        if (fileExist(files, fileName)) {
            try {
                InputStreamReader file = new InputStreamReader(getActivity().openFileInput(fileName));
                BufferedReader br = new BufferedReader(file);
                String line = br.readLine();
                String bitacoraCompleta = "";
                while (line != null) {
                    bitacoraCompleta = bitacoraCompleta + line + "\n";
                    line = br.readLine();
                }
                br.close();
                file.close();
                txt_file.setText(bitacoraCompleta);
            } catch (IOException e) {
                Toast.makeText(this.getActivity(), "Error al obtener el archivo", Toast.LENGTH_SHORT).show();
            }
        }

        binding.btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });


    }

    //metodo para probar si el archivo existe en los ficheros
    private boolean fileExist(String files[], String fileName) {
        for (int i = 0; i < files.length; i++)
            if (fileName.equals(files[i]))
                return true;
        return false;
    }

    //metodo para guardar el fichero con el multilinetext
    public void save() {
        try {
            OutputStreamWriter file = new OutputStreamWriter(getActivity().openFileOutput(fileName, Activity.MODE_PRIVATE));
            file.write(txt_file.getText().toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            Toast.makeText(this.getActivity(), "Error al obtener el archivo", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this.getActivity(), "Guardado exitosamente", Toast.LENGTH_SHORT).show();

        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}