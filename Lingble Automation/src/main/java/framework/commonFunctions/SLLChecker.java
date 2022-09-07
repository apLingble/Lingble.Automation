package framework.commonFunctions;


import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SLLChecker {


    public static void validateDNSExpiration(String environment)  {
        String partner_url = System.getProperty("url");
        String dns_url = environment.toLowerCase().equals("dev") || environment.toLowerCase().equals("development")? partner_url.replaceAll("www","dev"):partner_url;
        URL url = null;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        long timeDiffInDays = 0;
        try {
            url =  new URL(dns_url);
            Date date1 = myFormat.parse(convertDate(CheckCertificateExpiration(url)));
            Date date2 = myFormat.parse(commonHelper.generateTime("dd MM yyyy"));
            long diff = date1.getTime() - date2.getTime();
            timeDiffInDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {

        }catch (Exception e){

        }

        if (Math.abs(timeDiffInDays) < 7){
            System.out.println (dns_url+ " still have " + Math.abs(timeDiffInDays)+" days to expire");
            Assert.assertTrue( Math.abs(timeDiffInDays) > 7);
        }else {
            System.out.println (dns_url+ " still have " + Math.abs(timeDiffInDays)+" days to expire");
        }

    }

    public static String convertDate(String strDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = (Date)formatter.parse(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.DATE) + " " + (cal.get(Calendar.MONTH) + 1) + " " +cal.get(Calendar.YEAR);
        return formatedDate;
    }

    public static String CheckCertificateExpiration(URL url) throws Exception {
        // configure the SSLContext with a TrustManager
        String expiryDate=null;

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);

            //https://mms.nw.ru
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            Certificate[] certs = conn.getServerCertificates();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bais = new ByteArrayInputStream(certs[0].getEncoded());
            X509Certificate x509 = (X509Certificate) cf.generateCertificate(bais);
            expiryDate = x509.getNotAfter().toString();

            conn.disconnect();


        return expiryDate;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}

