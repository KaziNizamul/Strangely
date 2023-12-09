/* external imports */
import { useState, useEffect } from 'react';
/* utils */
import { EMPTY_OBJECT } from '../global.constant';

const useGeolocation = (options = EMPTY_OBJECT) => {
  const [location, setLocation] = useState();
  const [error, setError] = useState();
  // const [loading, setLoading] = useState(true);

  const handleSuccess = (position) => {
    const {
      latitude,
      longitude,
    } = position.coords || EMPTY_OBJECT;
    setLocation({
      latitude,
      longitude,
    });
    // setLoading(false);
  };

  const handleError = (err) => {
    setError(err);
    // setLoading(false);
  };

  useEffect(() => {
    let watcher;

    if (navigator.geolocation) {
      watcher = navigator.geolocation.watchPosition(
        handleSuccess,
        handleError,
        options,
      );
    } else {
      setError('Geolocation not supported');
      // setLoading(false);
    }

    return () => {
      navigator?.geolocation?.clearWatch(watcher);
    };
  }, [options]);

  return {
    location,
    error,
    // loading,
  };
};

export default useGeolocation;
