import { useEffect } from 'react';
import ReactWeather, { useOpenWeather } from 'react-open-weather';

export default function Weather() {

  const { data, isLoading, errorMessage } = useOpenWeather({
    key: 'c36b03a963176b9a639859e6cf279299',
    lat: '60.1733244',
    lon: '24.9410248',
    lang: 'en',
    unit: 'metric', // values are (metric, standard, imperial)
    showForecast: 'false',
  });

  return (
    <div>
      <ReactWeather
        isLoading={true}
        errorMessage={errorMessage}
        data={data}
        lang="en"
        locationLabel="Helsinki"
        unitsLabels={{ temperature: 'C', windSpeed: 'Km/h' }}
        showForecast={false}
      />
    </div>
  );
};