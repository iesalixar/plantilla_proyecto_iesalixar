import * as React from "react"
const CreateClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#00484c"
            d="M11 17h2v-4h4v-2h-4V7h-2v4H7v2h4zm-6 4q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h14q.825 0 1.413.588T21 5v14q0 .825-.587 1.413T19 21z"
        />
    </svg>
)
const HomeClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#00484c"
            d="M4 14.85V11q0-.2.075-.375T4.3 10.3l7-7q.15-.15.325-.225Q11.8 3 12 3t.375.075q.175.075.325.225l1.425 1.4ZM17 20v-8.175L13.075 7.9 15.2 5.775l4.5 4.525q.125.15.213.325Q20 10.8 20 11v8q0 .425-.288.712Q19.425 20 19 20ZM5 20q-.425 0-.713-.288Q4 19.425 4 19v-2.05l3-3V17h8.5v3Z"
        />
    </svg>
)
const LogoutClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 -0.9956 24 24"
        {...props}
    >
        <path
            fill="#00484c"
            d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h7v2H5v14h7v2zm11-4-1.375-1.45 2.55-2.55H9v-2h8.175l-2.55-2.55L16 7l5 5z"
        />
    </svg>
)

const NotificationClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 20 20"
        {...props}
    >
        <path
            fill="#00484c"
            fillRule="evenodd"
            d="M3.172 5.172a4 4 0 0 1 5.656 0L10 6.343l1.172-1.171a4 4 0 1 1 5.656 5.656L10 17.657l-6.828-6.829a4 4 0 0 1 0-5.656"
            clipRule="evenodd"
        />
    </svg>
)
const ProfileClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={42}
        height={36}
        className="sidebar-icon"
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#00484c"
            d="M12 11q.825 0 1.413-.588Q14 9.825 14 9t-.587-1.413Q12.825 7 12 7q-.825 0-1.412.587Q10 8.175 10 9q0 .825.588 1.412Q11.175 11 12 11Zm0 2q-1.65 0-2.825-1.175Q8 10.65 8 9q0-1.65 1.175-2.825Q10.35 5 12 5q1.65 0 2.825 1.175Q16 7.35 16 9q0 1.65-1.175 2.825Q13.65 13 12 13Zm0 11q-2.475 0-4.662-.938-2.188-.937-3.825-2.574Q1.875 18.85.938 16.663 0 14.475 0 12t.938-4.663q.937-2.187 2.575-3.825Q5.15 1.875 7.338.938 9.525 0 12 0t4.663.938q2.187.937 3.825 2.574 1.637 1.638 2.574 3.825Q24 9.525 24 12t-.938 4.663q-.937 2.187-2.574 3.825-1.638 1.637-3.825 2.574Q14.475 24 12 24Zm0-2q1.8 0 3.375-.575T18.25 19.8q-.825-.925-2.425-1.612-1.6-.688-3.825-.688t-3.825.688q-1.6.687-2.425 1.612 1.3 1.05 2.875 1.625T12 22Zm-7.7-3.6q1.2-1.3 3.225-2.1 2.025-.8 4.475-.8 2.45 0 4.463.8 2.012.8 3.212 2.1 1.1-1.325 1.713-2.95Q22 13.825 22 12q0-2.075-.788-3.887-.787-1.813-2.15-3.175-1.362-1.363-3.175-2.151Q14.075 2 12 2q-2.05 0-3.875.787-1.825.788-3.187 2.151Q3.575 6.3 2.788 8.113 2 9.925 2 12q0 1.825.6 3.463.6 1.637 1.7 2.937Z"
        />
    </svg>
)

const SearchClear = (props) => (
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width={32}
        height={32}
        viewBox="0 0 24 24"
        {...props}
    >
        <path
            fill="#00484c"
            d="m19.6 21-6.3-6.3q-.75.6-1.725.95T9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l6.3 6.3zM9.5 14q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5 6.313 6.313 5 9.5t1.313 3.188T9.5 14"
        />
    </svg>
)

export { CreateClear, HomeClear, LogoutClear, NotificationClear, ProfileClear, SearchClear };


