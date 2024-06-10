export function convertImageToBase64(file) {
    return new Promise((resolve, reject) => {
        if (!(file instanceof Blob)) {
            reject(new TypeError('The provided file is not of type Blob'));
            return;
        }

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
