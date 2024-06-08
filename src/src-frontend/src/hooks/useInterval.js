import { useEffect, useRef } from 'react';

// Hook personalizado para intervalos
const useInterval = (callback, delay) => {
    const savedCallback = useRef();

    // Guardar el último callback
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    // Establecer el intervalo
    useEffect(() => {
        const tick = () => {
            savedCallback.current();
        };
        if (delay !== null) {
            const id = setInterval(tick, delay);
            return () => clearInterval(id);
        }
    }, [delay]);
};

export default useInterval;
