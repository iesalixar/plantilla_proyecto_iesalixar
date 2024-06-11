import React, { useState, useEffect } from 'react';
import { resizeImage, toBase64 } from './imageService';

const ImageUploadComponent = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [resizedImage, setResizedImage] = useState(null);

    const handleFileChange = async (event) => {
        const file = event.target.files[0];
        if (file) {
            setSelectedFile(file);
            try {
                const resizedBlob = await resizeImage(file);
                const resizedBase64 = await toBase64(resizedBlob);
                setResizedImage(resizedBase64);
            } catch (error) {
                console.error("Error resizing image:", error);
                alert("Failed to resize image. Please try again.");
            }
        }
    };

    useEffect(() => {
        return () => {
            if (resizedImage) {
                URL.revokeObjectURL(resizedImage);
            }
        };
    }, [resizedImage]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!resizedImage) {
            alert('Please upload and resize an image first.');
            return;
        }

        const response = await fetch('http://your-backend-api.com/upload', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ image: resizedImage }),
        });

        if (response.ok) {
            alert('Image uploaded successfully');
        } else {
            const errorMessage = await response.text();
            alert(`Failed to upload image: ${errorMessage}`);
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input type="file" accept="image/*" onChange={handleFileChange} />
                <button type="submit">Upload Image</button>
            </form>
            {resizedImage && (
                <div>
                    <h3>Preview:</h3>
                    <img src={resizedImage} alt="Resized" style={{ maxWidth: '100%' }} />
                </div>
            )}
        </div>
    );
};

export default ImageUploadComponent;
