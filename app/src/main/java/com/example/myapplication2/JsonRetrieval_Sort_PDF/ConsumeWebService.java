package com.example.myapplication2.JsonRetrieval_Sort_PDF;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication2.R;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConsumeWebService extends AppCompatActivity {
    private String URLstring = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    public ListView lv;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;
    private File pdfFile;
    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.RECORD_AUDIO,
    };

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_web_service);
        lv = findViewById(R.id.lv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrieveJSON();
        Spinner spinner = (Spinner) findViewById(R.id.sort_by);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseContext(),R.array.sort_by_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        checkPermissions();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dm= dataModelArrayList.get(position);
                Intent i = new Intent(getApplicationContext(), ModifyItem.class);
                String imageUrl =dm.getImgURL();
                String name = dm.getName();
                String city = dm.getCity();
                String _id = dm.getId();
                String country = dm.getCountry();
                i.putExtra("name",name);
                i.putExtra("country",country);
                i.putExtra("city",city);
                i.putExtra("picture",imageUrl);
                i.putExtra("id",_id);
                startActivity(i);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("el item", String.valueOf(position));
                if(position==1){
                    Collections.sort(dataModelArrayList, new Comparator<DataModel>(){
                        public int compare(DataModel obj1, DataModel obj2) {
                            // ## Ascending order
                            return obj1.getName().compareToIgnoreCase(obj2.getName()); // To compare string values
                        }

                    });
                    setupListview();

                }
                else if(position ==2){
                    Collections.sort(dataModelArrayList, new Comparator<DataModel>(){
                        public int compare(DataModel obj1, DataModel obj2) {
                            // ## Ascending order
                            return obj1.getCity().compareToIgnoreCase(obj2.getCity()); // To compare string values
                            // return Integer.valueOf(obj1.empId).compareTo(Integer.valueOf(obj2.empId)); // To compare integer values

                            // ## Descending order
                            // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                            // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
                        }
                    });
                    setupListview();

                }
                else if (position ==3){
                    Collections.sort(dataModelArrayList, new Comparator<DataModel>(){
                        public int compare(DataModel obj1, DataModel obj2) {
                            // ## Ascending order
                            return obj1.getCountry().compareToIgnoreCase(obj2.getCountry()); // To compare string values
                            // return Integer.valueOf(obj1.empId).compareTo(Integer.valueOf(obj2.empId)); // To compare integer values

                            // ## Descending order
                            // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                            // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
                        }
                    });
                    setupListview();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void retrieveJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setCountry(dataobj.getString("country"));
                                    playerModel.setCity(dataobj.getString("city"));
                                    playerModel.setImgURL(dataobj.getString("imgURL"));
                                    playerModel.setId(dataobj.getString("id"));

                                    dataModelArrayList.add(playerModel);

                                }

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        listAdapter = new ListAdapter(this, dataModelArrayList);
        lv.setAdapter(listAdapter);

    }

    public void createPDF(View view) {
        create_pdf();
    }


    public void create_pdf()  {
        File docFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/docs");
        if (!docFolder.exists()) {
            docFolder.mkdir();
            Log.i("INFO", "Created a new directory for PDF");
        }
        String pdf_name ="tennis.pdf";
        pdfFile = new File(docFolder,"tennis.pdf");
        try{
            FileOutputStream output = new FileOutputStream(pdfFile);
            Document document = new Document(PageSize.A4);
            PdfPTable pdf_table = new PdfPTable(new float[]{3,3,3,3,3});
            pdf_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            pdf_table.getDefaultCell().setFixedHeight(50);
            pdf_table.setTotalWidth(PageSize.A4.getWidth());
            pdf_table.setWidthPercentage(100);
            pdf_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdf_table.addCell("id");
            pdf_table.addCell("Name");
            pdf_table.addCell("Country");
            pdf_table.addCell("City");
            pdf_table.addCell("Image");
            pdf_table.setHeaderRows(1);
            PdfPCell[] cells = pdf_table.getRow(0).getCells();
            for (int j=0;j<cells.length;j++){
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }

            for(int i=0;i<dataModelArrayList.size();i++){
                String id = dataModelArrayList.get(i).getId();
                String name = dataModelArrayList.get(i).getName();
                String country = dataModelArrayList.get(i).getCountry();
                String city = dataModelArrayList.get(i).getCity();
                String url =dataModelArrayList.get(i).getImgURL();
                pdf_table.addCell(String.valueOf(id));
                pdf_table.addCell(String.valueOf(name));
                pdf_table.addCell(String.valueOf(country));
                pdf_table.addCell(String.valueOf(city));
                PdfPCell pc=createImageCell(url);
                pdf_table.addCell(pc);

            }
            PdfWriter.getInstance(document,output);
            document.open();
            Font f = new Font(Font.FontFamily.TIMES_ROMAN,30.0f, Font.UNDERLINE,BaseColor.BLUE);
            Font g = new Font(Font.FontFamily.TIMES_ROMAN,20.0f, Font.NORMAL,BaseColor.BLUE);
            document.add(new Paragraph("PDF DATA \n\n",f));
            document.add(new Paragraph("PDF File through ITEXT \n\n",g));
            document.add(pdf_table);
            document.close();
            previewPDF();


    } catch (FileNotFoundException e){

        } catch ( DocumentException e){

        }

    }

    public void previewPDF(){
        PackageManager pm = getApplicationContext().getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setType("application/pdf");
        List list =pm.queryIntentActivities(i,PackageManager.MATCH_ALL);
        if(list.size()>0){
            Intent intent= new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri =Uri.fromFile(pdfFile);
            intent.setDataAndType(uri,"application/pdf");
            // etret a5le l targetsdk 23 3shan 3'er kda hntr n3ml fileprovider
            this.startActivity(intent);
        } else {
            Toast.makeText(this, "no app ", Toast.LENGTH_SHORT).show();
        }
    }

    public  PdfPCell createImageCell(String path)  {
        try{
            Bitmap bmp = fromURLtoBitmap(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image img = Image.getInstance(stream.toByteArray());
            PdfPCell cell = new PdfPCell(img, true);
            return cell;
        }
        catch (MalformedURLException e){
            return null;
        }
        catch (BadElementException e){
            return null;
        }
        catch (IOException e){
            return null;
        }
    }

    public Bitmap fromURLtoBitmap(String src) {
        // This exception is thrown when an application attempts to perform a networking operation
        // on its main thread. Run your code in AsyncTask:
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}

