import React, { useState, useEffect } from 'react';
// Función para convertir un archivo de imagen a base64

const ImagenService = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [resizedImage, setResizedImage] = useState(null);

    const handleFileChange = async (event) => {
        const file = event.target.files[0];
        if (file) {
            setSelectedFile(file);
            const base64Image = await convertImageToBase64(file);
            setResizedImage(base64Image);
        }
    };

    const resizeImage = (file) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const img = new Image();
            img.onload = () => {
                const canvas = document.createElement('canvas');
                const maxWidth = 568;
                const maxHeight = 584;
                let width = img.width;
                let height = img.height;

                // Mantener el ratio de aspecto
                if (width > maxWidth || height > maxHeight) {
                    const widthRatio = maxWidth / width;
                    const heightRatio = maxHeight / height;
                    const ratio = Math.min(widthRatio, heightRatio);
                    width = width * ratio;
                    height = height * ratio;
                }

                if (width <= 0 || height <= 0) {
                    alert("Invalid image dimensions.");
                    return;
                }

                canvas.width = width;
                canvas.height = height;
                const ctx = canvas.getContext('2d');
                ctx.drawImage(img, 0, 0, width, height);

                canvas.toBlob((blob) => {
                    if (!blob) {
                        alert("Failed to resize image. Please try again.");
                        return;
                    }
                    const resizedFile = new File([blob], file.name, { type: file.type });
                    setResizedImage(URL.createObjectURL(resizedFile));
                }, file.type);
            };
            img.src = event.target.result;
        };
        reader.onerror = (error) => {
            console.error("Error reading file:", error);
            alert("Failed to read file. Please try again.");
        };
        reader.readAsDataURL(file);
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

export default ImagenService;
