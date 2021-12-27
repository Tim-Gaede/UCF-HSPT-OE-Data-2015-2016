#include <stdio.h>
#include <stdlib.h>

using namespace std;

/*
This problem reduces to having n points that we want to create a polygon
from where every corner(vertex) of the polygon makes a 90 degree turn. If
we look at points on the same horizontal line they MUST pair up with the
next point on the same axis. We can use this same idea for the vertical axis.
Once we know the order of the points in our polygon we can easily compute
the area.
*/

struct point
{
    int x, y, vert, horz;
};
// Point array is global for sorting.
point pt[500];

// Compare points first by x value then y.
int compareXY(const void * a, const void * b)
{
    int xa = pt[*(int*)a].x;
    int xb = pt[*(int*)b].x;
    if(xa == xb)
    {
        int ya = pt[*(int*)a].y;
        int yb = pt[*(int*)b].y;
        return ya - yb;
    }
    return xa - xb;
}

// Compare points first by y value then x.
int compareYX(const void * a, const void * b)
{
    int ya = pt[*(int*)a].y;
    int yb = pt[*(int*)b].y;
    if(ya == yb)
    {
        int xa = pt[*(int*)a].x;
        int xb = pt[*(int*)b].x;
        return xa - xb;
    }
    return ya - yb;
}

int main()
{
    int tests;
    scanf("%d", &tests);

    for(int t = 1; t<=tests; t++)
    {
        int n;
        scanf("%d", &n);
        for(int i = 0; i<n; i++)
            scanf("%d %d", &pt[i].x, &pt[i].y);

        // We'll use this array to find the relative ordering of the points.
        int sorted[n];
        for(int i = 0; i<n; i++)
            sorted[i] = i;

        // Sort by x then y. Then pair every two points together.
        qsort(sorted, n, sizeof(int), compareXY);
        for(int i = 0; i<n; i+=2)
        {
            pt[sorted[i]].vert = sorted[i+1];
            pt[sorted[i+1]].vert = sorted[i];
        }

        // Do the same for the YX sorting.
        qsort(sorted, n, sizeof(int), compareYX);
        for(int i = 0; i<n; i+=2)
        {
            pt[sorted[i]].horz = sorted[i+1];
            pt[sorted[i+1]].horz = sorted[i];
        }

        // Now we have an ordering for our polygon.
        int area = 0;
        int prev = 0;
        int cur = pt[prev].horz;
        bool nextVert = true;
        while(cur != 0)
        {
            area += (pt[cur].x-pt[prev].x)*pt[cur].y;

            // Find the next vertex on the polygon.
            prev = cur;
            cur = nextVert ? pt[cur].vert : pt[cur].horz;

            // Alternate vertical and horizontal for 90 degree turns.
            nextVert = !nextVert;
        }
        // Our ordering might have been counterclock-wise so our area will be inverted.
        if(area < 0)
            area = -area;

        printf("Batch #%d: %d square centimeters\n", t, area);
    }
    return 0;
}
