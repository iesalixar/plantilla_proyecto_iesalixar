import React, { useRef, useState, useEffect } from "react";

import useFirstRender from "./useIsFirstRender";

import styles from "./styles.module.scss";

const DurationTrack = ({ numbers, qualifier, onChange, value }) => {
    const list = useRef(null);
    const firstRender = useFirstRender();

    useEffect(() => {
        const observer = new IntersectionObserver(
            (entries) => {
                const selectedEntry = entries.find(
                    (e) => Number.parseFloat(e.target.textContent) === value
                );
                selectedEntry?.target?.scrollIntoView();

                entries.forEach((entry) => {
                    if (!entry.isIntersecting) {
                        return;
                    }
                    !firstRender && onChange(Number.parseFloat(entry.target.textContent));
                });
            },
            { threshold: 1 }
        );

        [...list.current.children].forEach((child) => {
            observer.observe(child);
        });
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
