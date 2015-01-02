package ua.co.zen;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LightActivity extends Activity {

	private ImageButton toiletLight, hallLight, bedLight, kitchLight;
	private Button infoButton;
	public String toil_light, hall_light, bed_light, kitch_light;
	String URL = "http://192.168.1.33";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);

		infoButton = (Button) findViewById(R.id.infoButton);
		Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
		infoButton.setTypeface(tp);

		toiletLight = (ImageButton) findViewById(R.id.toiletLight);
		toil_light = MainActivity.toil_light;// Берем переменную из MainActivity

		hallLight = (ImageButton) findViewById(R.id.hallLight);
		hall_light = MainActivity.hall_light;// Берем переменную из MainActivity

		bedLight = (ImageButton) findViewById(R.id.bedroomLight);
		bed_light = MainActivity.bed_light;// Берем переменную из MainActivity

		kitchLight = (ImageButton) findViewById(R.id.kitchenLight);
		kitch_light = MainActivity.kitch_light;// Берем переменную из
												// MainActivity

		if (toil_light.equals("on")) {
			toiletLight.setImageResource(R.drawable.zen_light_1);
		} else {
			toiletLight.setImageResource(R.drawable.zen_light_black_5);
		}

		if (hall_light.equals("on")) {
			hallLight.setImageResource(R.drawable.zen_light_2);
		} else {
			hallLight.setImageResource(R.drawable.zen_light_black_6);
		}

		if (bed_light.equals("on")) {
			bedLight.setImageResource(R.drawable.zen_light_3);
		} else {
			bedLight.setImageResource(R.drawable.zen_light_black_7);
		}

		if (kitch_light.equals("on")) {
			kitchLight.setImageResource(R.drawable.zen_light_4);
		} else {
			kitchLight.setImageResource(R.drawable.zen_light_black_8);
		}

		// работает с помощь магии
		toiletLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (toil_light.equals("on")) { // если лампа была включена
					toiletLight.setImageResource(R.drawable.zen_light_black_5); // меняем
																				// картинку
					String addurl = "?toil_light=0"; // выключаем лампу
					new MainActivity.sendGet().execute(URL + addurl); // отправляем
																		// запрос
					infoButton.setText("Свет в спальне выключен");
					toil_light = "off"; // меняем статус
				} else { // проделываем обратные действия если лампа была
							// выключена
					toiletLight.setImageResource(R.drawable.zen_light_1);
					String addurl = "?toil_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("Свет в спальне включен");
					toil_light = "on";
				}
			}
		});

		// работает с помощь магии
		hallLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (hall_light.equals("on")) { // если лампа была включена
					hallLight.setImageResource(R.drawable.zen_light_black_6); // меняем
																				// картинку
					String addurl = "?hall_light=0"; // выключаем лампу
					new MainActivity.sendGet().execute(URL + addurl); // отправляем
																		// запрос
					infoButton.setText("Свет в коридоре выключен");
					hall_light = "off"; // меняем статус
				} else { // проделываем обратные действия если лампа была
							// выключена
					hallLight.setImageResource(R.drawable.zen_light_2);
					String addurl = "?hall_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("Свет в коридоре включен");
					hall_light = "on";
				}
			}
		});

		// работает с помощь магии
		bedLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bed_light.equals("on")) { // если лампа была включена
					bedLight.setImageResource(R.drawable.zen_light_black_7); // меняем
																				// картинку
					String addurl = "?bed_light=0"; // выключаем лампу
					new MainActivity.sendGet().execute(URL + addurl); // отправляем
																		// запрос
					infoButton.setText("Свет в спальне выключен");
					bed_light = "off"; // меняем статус
				} else { // проделываем обратные действия если лампа была
							// выключена
					bedLight.setImageResource(R.drawable.zen_light_3);
					String addurl = "?bed_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("Свет в спальне включен");
					bed_light = "on";
				}
			}
		});
		
		// работает с помощь магии
				kitchLight.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (kitch_light.equals("on")) { // если лампа была включена
							kitchLight.setImageResource(R.drawable.zen_light_black_8); // меняем
																						// картинку
							String addurl = "?kitch_light=0"; // выключаем лампу
							new MainActivity.sendGet().execute(URL + addurl); // отправляем
																				// запрос
							infoButton.setText("Свет на кухне выключен");
							kitch_light = "off"; // меняем статус
						} else { // проделываем обратные действия если лампа была
									// выключена
							kitchLight.setImageResource(R.drawable.zen_light_4);
							String addurl = "?kitch_light=1";
							new MainActivity.sendGet().execute(URL + addurl);
							infoButton.setText("Свет на кухне включен");
							kitch_light = "on";
						}
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
