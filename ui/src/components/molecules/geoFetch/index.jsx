/* external imports */
import { useEffect } from 'react';
import { message } from 'antd';
/* internal imports */
import useGeolocation from '../../../resources/shared/customHooks/UseGeoLocation';
/* constants */
import {
  EMPTY_FUNCTION,
  EMPTY_OBJECT,
} from '../../../resources/shared/global.constant';

function GeoFetch({
  onLocationUpdate = EMPTY_FUNCTION,
  options = EMPTY_OBJECT,
}) {
  const {
    location,
    error,
  } = useGeolocation(options);

  useEffect(() => {
    if (location) {
      onLocationUpdate(location);
    }
  }, [location, onLocationUpdate]);

  if (error) {
    message.error({
      content: error,
      duration: 2,
    });
  }
}

export default GeoFetch;
