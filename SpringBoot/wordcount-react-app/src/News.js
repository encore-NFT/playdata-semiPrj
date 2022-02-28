import React, { useEffect, useState } from "react";
import axios from "axios";
import KidNewsList from "./KidNewsList";

function News({url}) {
    // const substring = "kid";
    
    return <KidNewsList/>;
}

export default React.memo(News);