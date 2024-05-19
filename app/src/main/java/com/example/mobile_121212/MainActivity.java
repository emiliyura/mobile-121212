package com.example.mobile_121212;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Метод для сохранения данных в JSON файл.
     *
     * @param view View, по которой был произведен клик (не используется).
     */
    public void saveToJson(View view) {
        // Создаем новый объект JSONObject для хранения данных
        JSONObject jsonObject = new JSONObject();
        try {
            // Добавляем данные в JSON объект
            jsonObject.put("name", "John Doe");
            jsonObject.put("age", 30);
            jsonObject.put("isStudent", true);

            // Преобразуем JSON объект в строку JSON
            String jsonString = jsonObject.toString();

            // Открываем поток для записи данных в файл
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();

            // Показываем сообщение об успешном сохранении
            showToast("Saved to JSON file: " + jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для загрузки данных из JSON файла и отображения их.
     *
     * @param view View, по которой был произведен клик (не используется).
     */
    public void loadFromJson(View view) {
        try {
            // Открываем поток для чтения данных из файла
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            // Считываем данные из файла построчно
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            fis.close();

            // Преобразуем считанную строку JSON в JSON объект
            JSONObject jsonObject = new JSONObject(sb.toString());

            // Извлекаем данные из JSON объекта
            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");
            boolean isStudent = jsonObject.getBoolean("isStudent");

            // Показываем загруженные данные на экране приложения
            showToast("Loaded from JSON file:\nName: " + name + "\nAge: " + age + "\nIs Student: " + isStudent);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Отображает сообщение в виде Toast.
     *
     * @param message Сообщение для отображения.
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
