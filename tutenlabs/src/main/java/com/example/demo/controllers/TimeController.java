package com.example.demo.controllers;

import com.example.demo.model.Time;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TimeController {
    @PostMapping("/time")
    public String time(@RequestBody Time time) {

        if(time.getTime().isEmpty())
            return "EL campo time no puede ser vacío";

        if(time.getTimeZone().isEmpty())
            return "EL campo timeZone no puede ser vacío";
        SimpleDateFormat fromUser = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date fecha = null;
        try {
            fecha = fromUser.parse(time.getTime());
            calendar.setTime(fecha);
            calendar.add(Calendar.HOUR, Integer.parseInt(time.getTimeZone()));
            SimpleDateFormat DateFor = new SimpleDateFormat("HH:mm:ss");
            String stringDate= DateFor.format(calendar.getTime());
            return "{ \"response\":{" + " \"time\":\"" + stringDate + "\",\"timezone\":" + " \"UTC\"}}";

        } catch (ParseException e) {
            return "El formato de la fecha no es correcto, debe ser HH:mm:ss";
        }


    }
}
