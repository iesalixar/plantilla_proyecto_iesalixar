import React, { useRef, useState, useEffect } from "react";
import useFirstRender from "./useIsFirstRender";
import styles from "./styles.module.scss";

const DurationTrack = ({ numbers, qualifier, onChange, value }) => {
    const list = useRef(null);
    const firstRender = useFirstRender();

    useEffect(() => {
        const observer = new IntersectionObserver(
            (entries) => {
                // Encontrar la entrada más visible
                const selectedEntry = entries.reduce((mostVisible, entry) => {
                    return entry.intersectionRatio > mostVisible.intersectionRatio
                        ? entry
                        : mostVisible;
                }, { intersectionRatio: 0 });

                const selectedValue = Number.parseFloat(selectedEntry.target.textContent);
                if (selectedValue !== value) {
                    selectedEntry.target.scrollIntoView({ behavior: "smooth", block: "center" });
                    !firstRender && onChange(selectedValue);
                }
            },
            { threshold: [0.5] } // Ajustar el threshold
        );

        const children = [...list.current.children];
        children.forEach((child) => {
            observer.observe(child);
        });

        // Cleanup
        return () => {
            children.forEach((child) => {
                observer.unobserve(child);
            });
        };
    }, [value, onChange, firstRender]);

    return (
        <div className={styles.durationInput_trackWrapper}>
            <div className={styles.durationInput_track}>
                <div className={styles.durationInput_list} ref={list}>
                    {numbers.map((number) => (
                        <div
                            className={styles.durationInput_number}
                            key={qualifier + number}
                        >
                            {number} {qualifier}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

const DurationInput = () => {
    const [hours, setHours] = useState(0);
    const [minutes, setMinutes] = useState(0);

    useEffect(() => {
        console.log("minutes changed", minutes);
    }, [minutes]);

    useEffect(() => {
        console.log("hours changed", hours);
    }, [hours]);

    return (
        <div className={styles.durationInput}>
            <DurationTrack
                numbers={[...Array(24).keys()]}
                value={hours}
                qualifier="hours"
                onChange={setHours}
            />
            <DurationTrack
                numbers={[...Array(60).keys()]}
                value={minutes}
                qualifier="minutes"
                onChange={setMinutes}
            />
        </div>
    );
};

export default DurationInput;
