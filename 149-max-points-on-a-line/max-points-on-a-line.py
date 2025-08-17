class Solution(object):
    def maxPoints(self, points):
        MaxNbPoints=1
        for l in range(len(points)-1):
            for r in range(len(points)-1,l,-1):
                CurNbPoints=2
                for k in range(l+1,r):
                    if (points[k][1]-points[l][1])*(points[r][0]-points[l][0])==(points[k][0]-points[l][0])*(points[r][1]-points[l][1]):
                        CurNbPoints+=1
                MaxNbPoints=max(CurNbPoints,MaxNbPoints)
        return MaxNbPoints