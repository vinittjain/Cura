package com.example.unknown_joker.cura;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Shresth on 20-04-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    static String Id="",result="";
    private Context mContext;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;


    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String method= params[0];
      /*  String reg_url = "http://helpfightepidemic.com/infection_register.php";
        String login_url = "http://helpfightepidemic.com/return_id.php";
        String migrate_url = "http://helpfightepidemic.com/migrate.php";
        String govt_corner_url = "http://helpfightepidemic.com/govt_corner.php";
        String cyber_quarantine_url="http://helpfightepidemic.com/cyber_quarantine.php";
        String recovered_url="http://helpfightepidemic.com/recovered.php";
        String result_url="http://helpfightepidemic.com/result.php"; */

        String reg_url = "http://salabs.in/infection_register.php";
        String login_url = "http://salabs.in/return_id.php";
        String migrate_url = "http://salabs.in/migrate.php";
        String govt_corner_url = "http://salabs.in/govt_corner.php";
        String cyber_quarantine_url="http://salabs.in/cyber_quarantine.php";
        String recovered_url="http://salabs.in/recovered.php";
        String result_url="http://makerssteam-com.stackstaging.com/retrieve.php";




        String response ="",response1="";

        if(method.equals("register"))
        {

           try {
                URL url1 =new URL(login_url);
                HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoOutput(true);
                httpURLConnection1.setDoInput(true);
                OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1= new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String data1 = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode("name","UTF-8");
                bufferedWriter1.write(data1);
                bufferedWriter1.flush();
                bufferedWriter1.close();

                InputStream inputStream1 = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String line="";
                while ((line = bufferedReader1.readLine())!=null)
                {

                    response+=line;
                    //return response;

                }
                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


           String zipcode=params[1];
            String date=params[2];
            String disease= params[3];
            try {
                URL url =  new URL (reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("disease","UTF-8")+"="+ URLEncoder.encode(disease,"UTF-8")+"&"+
                        URLEncoder.encode("zipcode","UTF-8")+"="+ URLEncoder.encode(zipcode,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+ URLEncoder.encode(date,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                response =Integer.toString(Integer.parseInt(response)+1);



                Id=response;
                SharedPreferences putdat = ctx.getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor edito =putdat.edit();
                edito.putString("id",Id);
                edito.putBoolean("infected",true);
                edito.commit();


                return "Registration Success..."+"infection ID = "+response;





            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        else if (method.equals("migrant"))
        {

            String zipcode=params[1];
            String id= params[2];
           
            try {
                URL url =  new URL (migrate_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("zipcode","UTF-8")+"="+ URLEncoder.encode(zipcode,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();





                SharedPreferences putdat = ctx.getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor edito =putdat.edit();
                edito.putBoolean("migrant",true);
                edito.commit();


                return "Migrate success";





            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }




        }
        else if (method.equals("govt_corner"))
        {
            String medication=params[1];
            String vaccination=params[2];
            String isolation= params[3];
            String zipcode= params[4];
            String disease= params[5];
            String date= params[6];

            try {
                URL url5 =  new URL (govt_corner_url);
                HttpURLConnection httpURLConnection5 = (HttpURLConnection) url5.openConnection();
                httpURLConnection5.setRequestMethod("POST");
                httpURLConnection5.setDoOutput(true);
                OutputStream OS5 = httpURLConnection5.getOutputStream();
                BufferedWriter bufferedWriter5 = new BufferedWriter(new OutputStreamWriter(OS5,"UTF-8"));
                String data5=
                        URLEncoder.encode("medication","UTF-8")+"="+ URLEncoder.encode(medication,"UTF-8")+"&"+
                                URLEncoder.encode("vaccination","UTF-8")+"="+ URLEncoder.encode(vaccination,"UTF-8")+"&"+
                        URLEncoder.encode("isolation","UTF-8")+"="+ URLEncoder.encode(isolation,"UTF-8")+"&"+
                        URLEncoder.encode("zipcode","UTF-8")+"="+ URLEncoder.encode(zipcode,"UTF-8")+"&"+
                        URLEncoder.encode("disease","UTF-8")+"="+ URLEncoder.encode(disease,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+ URLEncoder.encode(date,"UTF-8");
                bufferedWriter5.write(data5);
                bufferedWriter5.flush();
                bufferedWriter5.close();
                OS5.close();
                InputStream IS5 = httpURLConnection5.getInputStream();
                IS5.close();

                return "mitigation successfull ";





            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        else if (method.equals("cyber_quarantine"))
        {
            String zipcode=params[1];


            try {
                URL url5 =  new URL (cyber_quarantine_url);
                HttpURLConnection httpURLConnection5 = (HttpURLConnection) url5.openConnection();
                httpURLConnection5.setRequestMethod("POST");
                httpURLConnection5.setDoOutput(true);
                OutputStream OS5 = httpURLConnection5.getOutputStream();
                BufferedWriter bufferedWriter5 = new BufferedWriter(new OutputStreamWriter(OS5,"UTF-8"));
                String data5= URLEncoder.encode("zipcode","UTF-8")+"="+ URLEncoder.encode(zipcode,"UTF-8");
                bufferedWriter5.write(data5);
                bufferedWriter5.flush();
                bufferedWriter5.close();
                OS5.close();
                InputStream IS5 = httpURLConnection5.getInputStream();
                IS5.close();

                return "cyber qurantine successful ";





            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        else if (method.equals("recovered"))
        {
            String id=params[1];


            try {
                URL url5 =  new URL (recovered_url);
                HttpURLConnection httpURLConnection5 = (HttpURLConnection) url5.openConnection();
                httpURLConnection5.setRequestMethod("POST");
                httpURLConnection5.setDoOutput(true);
                OutputStream OS5 = httpURLConnection5.getOutputStream();
                BufferedWriter bufferedWriter5 = new BufferedWriter(new OutputStreamWriter(OS5,"UTF-8"));
                String data5= URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(id,"UTF-8");
                bufferedWriter5.write(data5);
                bufferedWriter5.flush();
                bufferedWriter5.close();
                OS5.close();
                InputStream IS5 = httpURLConnection5.getInputStream();
                IS5.close();

                return "recovered ";





            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        else if (method.equals("result"))
        {

            try {
                URL url1 =new URL(result_url);
                HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoOutput(true);
                httpURLConnection1.setDoInput(true);
                OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1= new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String data1 = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode("name","UTF-8");
                bufferedWriter1.write(data1);
                bufferedWriter1.flush();
                bufferedWriter1.close();

                InputStream inputStream1 = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String line="";
                while ((line = bufferedReader1.readLine())!=null)
                {

                    response1+=line;
                    //return response;

                }
                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result=response1;
            SharedPreferences putdata = ctx.getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor edito =putdata.edit();
            edito.putString("result",result);
            edito.commit();
            return result;




        }

        else if (method.equals("angle_data"))
        {

            try {
                URL url1 =new URL(result_url);
                HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoOutput(true);
                httpURLConnection1.setDoInput(true);
                OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1= new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String data1 = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode("name","UTF-8");
                bufferedWriter1.write(data1);
                bufferedWriter1.flush();
                bufferedWriter1.close();

                InputStream inputStream1 = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));

                String line="";
                while ((line = bufferedReader1.readLine())!=null)
                {

                    response1+=line;
                    //return response;

                }
                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result=response1;
            SharedPreferences putdata = ctx.getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor edito =putdata.edit();
            edito.putString("result",result);
            edito.commit();
            return result;




        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
      // Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        function.setDefaults("val",result,ctx);
    }
}
