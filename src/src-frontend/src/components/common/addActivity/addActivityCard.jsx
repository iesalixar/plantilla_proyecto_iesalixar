import React, { useState, useRef, useContext, useEffect } from 'react';

import { useAuthContext } from '../../../contexts/AuthProvider';
import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useModalContext } from '../../../contexts/ModalProvider';

import { createActivity } from '../../../service/activityService';
import TimeInput from './components/timeInput/timeInput';
import { CloseIconClear, CloseIconDark } from './components/icons/closeIcon';
import { AddImageIconClear, AddImageIconDark } from './components/icons/addImageIcon';

import activityTypes from '../../../model/ActivityTypes';

import './style.scss';

function convertImageToBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.onload = () => {
            resolve(reader.result);
        };

        reader.onerror = (error) => {
            reject(error);
        };
    });
}

const AddActivityForm = ({ style }) => {
    const { userData } = useAuthContext();
    const { theme, isDark } = useContext(ThemeContext);
    const { closeModal } = useModalContext();

    const [activityType, setActivityType] = useState('');
    const [title, setTitle] = useState('');
    const [place, setPlace] = useState('');
    const [duration, setDuration] = useState({ hours: '', minutes: '' });
    const [image, setImage] = useState(null);
    const [friends, setFriends] = useState('');
    const [activitySuggestions, setActivitySuggestions] = useState([]);
    const [showSuggestions, setShowSuggestions] = useState(false);
    const inputRef = useRef(null);
    const suggestionsRef = useRef(null);

    const formatDuration = () => {
        const { hours, minutes } = duration;
        return `${hours} hours - ${minutes} min`;
    };

    useEffect(() => {
        if (!userData) {
            console.log('No user data found in sessionStorage');
        }

        const handleKeyDown = (e) => {
            if (e.key === 'Escape') {
                setShowSuggestions(false);
            }
        };

        const handleClickOutside = (e) => {
            if (suggestionsRef.current && !suggestionsRef.current.contains(e.target) && !inputRef.current.contains(e.target)) {
                setShowSuggestions(false);
            }
        };

        document.addEventListener('keydown', handleKeyDown);
        document.addEventListener('mousedown', handleClickOutside);

        return () => {
            document.removeEventListener('keydown', handleKeyDown);
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [userData]);

    if (!userData) {
        return <div>Loading...</div>;
    }

    const closeIcon = isDark ? <CloseIconDark /> : <CloseIconClear />;
    const addImageIcon = isDark ? <AddImageIconDark /> : <AddImageIconClear />;

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImage(file);
    };

    const handleActivityChange = (e) => {
        const input = e.target.value;
        setActivityType(input);
        const suggestions = activityTypes.filter(activity => activity.toLowerCase().includes(input.toLowerCase()));
        setActivitySuggestions(suggestions);
        setShowSuggestions(true);
    };

    const handleActivityClick = (activity) => {
        setActivityType(activity);
        setActivitySuggestions([]);
        setShowSuggestions(false);
    };

    const handleImageClick = () => {
        inputRef.current.click();
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const imageBase64 = await convertImageToBase64(image);
            const formattedDuration = `${duration.hours} hours - ${duration.minutes} min`;


            const activityData = {
                type: activityType,
                title,
                place,
                duration: formattedDuration,
                date: null,
                likes: 0,
                creator: userData.user,
                image: imageBase64,
                comments: [],
                participants: [],
            };
            console.log(activityData)
            const token = userData.token;

            createActivity(activityData, token)
                .then((data) => {
                    console.log('Activity created successfully:', data);
                })
                .catch((error) => {
                    console.error('Error creating activity:', error);
                });
        } catch (error) {
            console.error('Error converting image to base64:', error);
        }
    };

    return (
        <div className='modal-container'>
            <div className='add-activity-card' style={{ borderColor: theme.gray7, background: theme.gray3, color: theme.teal12 }}>
                <div className='card-header-container'>
                    <div className='title-container'><h2>Add Publication</h2></div>
                    <button onClick={closeModal} style={{ background: theme.gray3 }}>{closeIcon}</button>
                </div>
                <form onSubmit={handleSubmit} className='publication-form'>
                    <div className="input-container" ref={inputRef}>
                        <input type="text" value={activityType} onChange={handleActivityChange} style={{ borderColor: theme.gray7, color: theme.gray12 }} placeholder='Type' />
                        {showSuggestions && activitySuggestions.length > 0 && (
                            <ul className="suggestions-list" style={{ backgroundColor: theme.gray4, borderColor: theme.gray7 }} ref={suggestionsRef}>
                                {activitySuggestions.map((activity, index) => (
                                    <li key={index} onClick={() => handleActivityClick(activity)} style={{ background: theme.grayA4 }}>{activity}</li>
                                ))}
                            </ul>
                        )}
                    </div>
                    <div className="input-container">
                        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} style={{ borderColor: theme.gray7, color: theme.gray12 }} placeholder='Title' />
                    </div>
                    <div className="input-container">
                        <input type="text" value={place} onChange={(e) => setPlace(e.target.value)} style={{ borderColor: theme.gray7, color: theme.gray12 }} placeholder='Place' />
                    </div>
                    <div className="input-container">
                        <TimeInput
                            label="Event Time"
                            onHourChange={(hour) => setDuration({ ...duration, hours: hour })}
                            onMinuteChange={(minute) => setDuration({ ...duration, minutes: minute })}
                        />
                    </div>
                    <div className="input-container">
                        <input type="text" value={friends} readOnly style={{ borderColor: theme.gray7, color: theme.gray12 }} placeholder='Add Friends' />
                    </div>
                    <div className="input-container-image-button" style={{ color: theme.teal12 }}>
                        <input type="file" accept="image/*" style={{ display: 'none' }} onChange={handleImageChange} ref={inputRef} />
                        <button type="button" onClick={handleImageClick} className="image-upload-btn" style={{ background: theme.grayA4, color: theme.teal12 }}>Upload an Image {addImageIcon}</button>
                    </div>
                    <div className='submit-button-container'>
                        <button type="submit" style={{ backgroundColor: theme.teal12, color: theme.gray1 }}>Publish</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddActivityForm;
