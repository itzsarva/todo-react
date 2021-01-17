import React, { useState } from "react";
import "./styles.css";
import './formUtils.css';

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export default function CarRentalDatePicker() {
  const [date, setDate] = useState(new Date());
  const handleChange = date => setDate(date);

  const today = new Date();
  let in3Days = new Date();
  in3Days.setDate(in3Days.getDate() + 3);

  return (
    <DatePicker
      selected={date}
      onChange={handleChange}
      minDate={today}
      maxDate={in3Days}
      showTimeSelect
      dateFormat="MMMM d, yyyy h:mm aa"
    />
  );
}