package com.amalitech.advancedwebsort.Service;

import com.amalitech.advancedwebsort.Requests.SortRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SortingService {

    public List<?> Sorting(SortRequest sortRequest);
}
